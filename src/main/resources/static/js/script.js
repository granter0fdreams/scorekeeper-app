const userName = 'userID.userName';

const ws = new WebSocket('ws://localhost:8080');

ws.onmessage = event => {
    const chatBox = document.getElementById('chat-box');
    const messageDiv = document.createElement('div');
    messageDiv.ChatBoxClass = 'message';
    messageDiv.textContent = event.data;

    chatBox.appendChild(messageDiv);

};

function sendMessage() {
    const messageInput = document.getElementById('input-message').value;
        if(messageInput.trim() === '') {

        return;
        }
        ws.send(`${userName}: ${messageInput}`);

        document.getElementId('input-message').value = '';
}