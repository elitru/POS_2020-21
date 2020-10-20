const pizzaAmounts = document.getElementsByClassName("amount");
const delivery = document.getElementById("deliver-to");

const isValid = () => {
    let atLeastOne = false;
    
    for(const entry of pizzaAmounts) {
        if(+entry.value > 0)
            atLeastOne = true;
    }
    
    const valid = atLeastOne && delivery.value;
    
    if(!valid) {
        alert("Es muss mindestens eine Pizza ausgewählt und die Adresse angegeben werden!");
        return false;
    }
    
    return valid;
};

const onChangeLanguage = (event) => {
    const cookie = document.cookie.replace(/^lang=[0,1]/, `lang=${event.target.value}`);
    document.cookie = cookie;
    window.location.reload();
};