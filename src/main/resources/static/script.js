const inputBox = document.getElementById("input-box");
const listContainer = document.getElementById("list-container");

// Função para lidar com o evento de arrastar iniciado
function handleDragStart(e) {
    e.dataTransfer.setData("text/plain", e.target.innerHTML);
    e.target.classList.add("is-dragging"); // Adiciona classe de arrastando
}


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
    e.target.classList.remove("is-dragging"); // Remove classe de arrastando
    
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

function goToAccountPage() {
    window.location.href = 'myaccount.html';
}

function goToHomePage() {
    window.location.href = 'index.html';
}

let taskIdCounter = 0; // Variável para contar o número de tarefas

function addTask(){
    if(inputBox.value == ''){
        alert("You must write something!");
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

listContainer.addEventListener("click", function(e){
    if(e.target.tagName == "LI"){
        e.target.classList.toggle("checked");
        saveData();
    }
    else if(e.target.tagName == "SPAN"){
        e.target.parentElement.remove();
        saveData();
    }
}, false);

function saveData(){
    localStorage.setItem("data", listContainer.innerHTML);
}

function showTask(){
    if (localStorage.getItem("data")) {
        listContainer.innerHTML = localStorage.getItem("data");
    }
    let editButtons = document.querySelectorAll("li button");
    editButtons.forEach(button => {
        button.onclick = function () {
            let li = this.parentElement;
            let newText = prompt("Edit your task:", li.firstChild.textContent);
            if (newText !== null && newText.trim() !== '') {
                li.firstChild.textContent = newText;
                saveData();
            }
        };
    });
}
// localStorage.clear();
showTask();

// Adiciona eventos de arrastar às tarefas existentes
const tasks = document.querySelectorAll("#list-container li");
tasks.forEach(task => {
    task.addEventListener("dragstart", handleDragStart);
});

// Adiciona eventos de arrastar às tarefas adicionadas dinamicamente
listContainer.addEventListener("DOMNodeInserted", function(e) {
    if (e.target.tagName === "LI") {
        e.target.addEventListener("dragstart", handleDragStart);
    }
});


// Carrega as tarefas salvas
if (localStorage.getItem("data")) {
    listContainer.innerHTML = localStorage.getItem("data");
}
