const submitButton = document.getElementById("submitForm");
const deleteInput = document.getElementById("deleteInput");

const onSubmitForm = () => {
    submitButton.click();
};

const onDelete = (id) => {
    if(!confirm("Dou you really want to delete this contact?")) {
        return;
    }
    
    deleteInput.value = id;
    submitButton.click();
};