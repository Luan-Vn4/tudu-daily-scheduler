const inputBox = document.getElementById("input-box");
const listContainer = document.getElementById("list-container");

function addTask(){
    if(inputBox.value == ''){
        alert("You must write something!");
    }
    else{
        let li = document.createElement("li");
        li.innerHTML = inputBox.value;

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
    }
    inputBox.value = "";
    saveData();
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