const subjectInput = document.getElementById('subject');
const subjectError = document.getElementById('subject-error');
    
const teacherInput = document.getElementById('teacher');
const teacherError = document.getElementById('teachers-error');

const isValid = () => {
    let valid = true;
    if(!subjectInput.value) {
        subjectError.innerHTML = 'Sie müssen ein Fach angeben.';
        valid = false;
    } else {
        subjectError.innerHTML = '';
    }
    
    if(!teacherInput.value) {
        teacherError.innerHTML = 'Sie müssen mindestens einen Lehrer angeben.';
        valid = false;
    } else {
        teacherError.innerHTML = '';
    }
    
    return valid;
};