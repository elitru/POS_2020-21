const nicknameInput = document.getElementById('nickname');
const emailInput = document.getElementById('email');
const commandInput = document.getElementById('command');

const validate = () => {
    const nickname = nicknameInput.value;
    const email = emailInput.value;
    const command = commandInput.value;
        
    return nickname && email && command;
};