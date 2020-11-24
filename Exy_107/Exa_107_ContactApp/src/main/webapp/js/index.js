const onloadFavs = () => {
    const htmlItems = document.getElementsByClassName('fcon');
    const result = [];
    
    for(const item of htmlItems) {
        if(item.classList.contains('fav-marked')) {
            if(item.id) {
                result.push(+item.id.split('-')[1]);
            }
        }
    }
    
    return result;
};

const submitButton = document.getElementById("submitForm");
const deleteInput = document.getElementById("deleteInput");
const favInput = document.getElementById("favInput");
const deleteInputWithDelete = document.getElementById("submitFormWithDelete");
const form = document.getElementById("form");
const toBeDeleted = [];
const favs = onloadFavs();

const onSubmitForm = () => {
    deleteInput.value = "";
    favInput.value = favsToString();
    form.submit();
};

const onSubmitWithDelete = () => {
    let toDelete = "";
    
    for(let i = 0; i < toBeDeleted.length; i++) {
        toDelete += toBeDeleted[i];
        
        if(i < toBeDeleted.length - 1) {
            toDelete += ";";
        }
    }
    
    deleteInput.value = toDelete;
    favInput.value = favsToString();
    form.submit();
};

const favsToString = () => {
    let favsString = "";
    
    for(let i = 0; i < favs.length; i++) {
        favsString += favs[i];
        
        if(i < favs.length - 1) {
            favsString += ";";
        }
    }
    
    return favsString;
};

const onSwitchDelete = (id) => {
    const el = document.getElementById('delete-' + id);
    
    if(!toBeDeleted.includes(id)) {
        toBeDeleted.push(id);
        el.classList.add('marked');
    }else{
        toBeDeleted.splice(toBeDeleted.indexOf(id), 1);
        el.classList.remove('marked');
    }
    
    if(toBeDeleted.length > 0) {
        deleteInputWithDelete.disabled = false;
    }else{
        deleteInputWithDelete.disabled = true;
    }
};

const onSwitchFav = (id) => {
    const el = document.getElementById('fav-' + id);
    
    if(!favs.includes(id)) {
        favs.push(id);
        el.classList.add('fav-marked');
    }else{
        favs.splice(favs.indexOf(id), 1);
        el.classList.remove('fav-marked');
    }
    console.log(favs)
    onSubmitForm();
};

const downloadFavourites = async () => {
  const res = await fetch(window.location.href.replace('ContactController', 'DownloadController'));
  
  if(res.ok) {
    const file = await res.blob();
    downloadFile(file, "favourites.json", "application/json");
    return;
  }
  
  alert('An error occured during download! Look into the consolefor more information');
  console.log(res.statusText);
};

const downloadFile = (blob, filename, mime) => {
  if (typeof window.navigator.msSaveBlob !== 'undefined') {
    window.navigator.msSaveBlob(blob, filename);
    return;
  }
  
  const blobURL = window.URL.createObjectURL(blob);
  const tempLink = document.createElement('a');
  tempLink.style.display = 'none';
  tempLink.href = blobURL;
  tempLink.setAttribute('download', filename);
 
  if (typeof tempLink.download === 'undefined') {
    tempLink.setAttribute('target', '_blank');
  }
  document.body.appendChild(tempLink);
  tempLink.click();
  document.body.removeChild(tempLink);
  setTimeout(() => {
    window.URL.revokeObjectURL(blobURL);
  }, 100);
}

window.onload = () => deleteInputWithDelete.disabled = true;