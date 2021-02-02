const cityInput = document.getElementById("city");
const cityError = document.getElementById("cityErr");

const onSubmit = () => {
    const value = cityInput.value;
    
    if(!value) {
        cityError.style.visibility = "visible";
        return false;
    }
    
    return true;
}

const onChangeLanguage = () => {
  document.getElementById("form").submit();
};