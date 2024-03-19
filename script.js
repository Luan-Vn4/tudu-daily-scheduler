const inputBox = document.getElementById("input-box");
const listContainer = document.getElementById("list-container");

// URL da API local
const apiUrl = 'http://localhost:8080/tudu/api/tasks/?user-id=';

// Função para lidar com o evento de arrastar iniciado
function handleDragStart(e) {
    e.dataTransfer.setData("text/plain", e.target.innerHTML);
    e.target.classList.add("is-dragging"); // Adiciona classe de arrastando
}

// Função para permitir a queda na zona de soltar
function allowDrop(e) {
    e.preventDefault();
}

// Função para lidar com o evento de soltar
function handleDrop(e) {
    e.preventDefault();
    const data = e.dataTransfer.getData("text/plain");
    const li = document.createElement("li");
    li.innerHTML = data;
    this.querySelector("ul").appendChild(li);
    e.target.classList.remove("is-dragging");

    // Remover o item da lista original pelo texto
    const originalList = document.getElementById("list-container");
    const originalItem = Array.from(originalList.querySelectorAll("li")).find(item => item.innerHTML === data);
    if (originalItem) {
         originalItem.remove();
    }
    saveData();
}

function handleDelete(e) {
    const item = e.target.closest("li");
    if (item) {
        item.remove();
        saveData();
    }
}

// Função para adicionar tarefa
function addTask(){
    if(inputBox.value == ''){
        alert("Você precisa escrever algo!");
    }
    else{
        let li = document.createElement("li");
        let taskId = "task_" + taskIdCounter++; // Incrementa o contador para obter um ID único
        li.id = taskId;
        li.innerHTML = inputBox.value;
        li.setAttribute("draggable", "true");
        li.classList.add("task");

        let span = document.createElement("span");
        span.innerHTML = "\u00d7";
        span.className = "delete";
        span.onclick = function() {
            let div = this.parentElement;
            div.style.display = "none";
            saveData();
        };
        li.appendChild(span);

        let editButton = document.createElement("button");
        editButton.innerHTML = "Edit";
        editButton.classList.add("edit-button");
        editButton.onclick = function() {
            let newText = prompt("Edit your task:", li.firstChild.textContent);
            if (newText !== null && newText.trim() !== '') {
                li.firstChild.textContent = newText;
                saveData();
            }
        };
        li.appendChild(editButton);

        listContainer.appendChild(li);
        li.addEventListener("dragstart", handleDragStart);

        saveData();
    }
    inputBox.value = "";
}

// Função para salvar os dados no servidor //
function saveDataToServer() {
    const tasks = Array.from(document.querySelectorAll("#list-container li")).map(task => task.textContent);
    const userId = getUserId();
    fetch(apiUrl + userId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ tasks }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Falha em salvar as Tasks no Servidor');
        }
        console.log('As Tasks foram salvas com êxito!');
    })
    .catch(error => {
        console.error('Ocorreu um erro ao salvar as Task no Servidor:', error);
    });
}

// Função para carregar os dados do servidor
function loadDataFromServer() {
    const userId = getUserId();
    fetch(apiUrl + userId)
    .then(response => {
        if (!response.ok) {
            throw new Error('Não conseguimos carregar sua Task. Perdão!');
        }
        return response.json();
    })
    .then(data => {
        if (data && data.tasks) {
            listContainer.innerHTML = '';
            data.tasks.forEach(task => {
                const li = document.createElement("li");
                li.innerHTML = task;
                li.setAttribute("draggable", "true");
                li.classList.add("task");

                let span = document.createElement("span");
                span.innerHTML = "\u00d7";
                span.className = "delete";
                span.onclick = function() {
                    let div = this.parentElement;
                    div.style.display = "none";
                    saveData();
                };
                li.appendChild(span);

                let editButton = document.createElement("button");
                editButton.innerHTML = "Edit";
                editButton.classList.add("edit-button");
                editButton.onclick = function() {
                    let newText = prompt("Edit your task:", li.firstChild.textContent);
                    if (newText !== null && newText.trim() !== '') {
                        li.firstChild.textContent = newText;
                        saveData();
                    }
                };
                li.appendChild(editButton);

                listContainer.appendChild(li);
                li.addEventListener("dragstart", handleDragStart);
            });
        }
    })
    .catch(error => {
        console.error('Tivemos um erro ao carregar sua Task:', error);
    });
}

// Função para obter o ID do usuário (Substituir caso não funcionar)
function getUserId() {
    return 'luan123';
}

// Carrega as tarefas salvas
loadDataFromServer();

// Adiciona eventos de zona de queda
const todoTab = document.getElementById("todoTab");
const inProgressTab = document.getElementById("inProgressTab");
const doneTab = document.getElementById("doneTab");

todoTab.addEventListener("dragover", allowDrop);
inProgressTab.addEventListener("dragover", allowDrop);
doneTab.addEventListener("dragover", allowDrop);

todoTab.addEventListener("drop", handleDrop);
inProgressTab.addEventListener("drop", handleDrop);
doneTab.addEventListener("drop", handleDrop);

// Adiciona eventos de arrastar às tarefas existentes
const tasks = document.querySelectorAll("#list-container li");
tasks.forEach(task => {
    task.addEventListener("dragstart", handleDragStart);
});


listContainer.addEventListener("DOMNodeInserted", function(e) {
    if (e.target.tagName === "LI") {
        e.target.addEventListener("dragstart", handleDragStart);
    }
});

window.addEventListener('beforeunload', saveDataToServer);