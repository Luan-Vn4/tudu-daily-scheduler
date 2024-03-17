const form = document.getElementById("todo-form");
const input = document.getElementById("todo-input");
const todoLane = document.getElementById("todo-lane");
const inProcessLane = document.getElementById("in-process-lane");
const doneLane = document.getElementById("done-lane");

window.onload = function() {
  const savedTasks = JSON.parse(localStorage.getItem("tasks"));
  if (savedTasks) {
    savedTasks.forEach(task => {
      createTask(task.text, task.status);
    });
  }
};

function createTask(value, status) {
  const newTask = document.createElement("p");
  newTask.classList.add("task");
  newTask.setAttribute("draggable", "true");
  newTask.innerText = value;

  newTask.addEventListener("dragstart", () => {
    newTask.classList.add("is-dragging");
  });

  newTask.addEventListener("dragend", () => {
    newTask.classList.remove("is-dragging");
  });

  switch (status) {
    case 'todo':
      todoLane.appendChild(newTask);
      break;
    case 'in-process':
      inProcessLane.appendChild(newTask);
      break;
    case 'done':
      doneLane.appendChild(newTask);
      break;
  }
}

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const value = input.value;

  if (!value) return;

  createTask(value, 'todo');


  const savedTasks = JSON.parse(localStorage.getItem("tasks")) || [];
  savedTasks.push({ text: value, status: 'todo' });
  localStorage.setItem("tasks", JSON.stringify(savedTasks));

  input.value = "";
});

const droppables = document.querySelectorAll(".swim-lane");

droppables.forEach((zone) => {
  zone.addEventListener("dragover", (e) => {
    e.preventDefault();

    const bottomTask = insertAboveTask(zone, e.clientY);
    const curTask = document.querySelector(".is-dragging");

    if (!bottomTask) {
      zone.appendChild(curTask);
    } else {
      zone.insertBefore(curTask, bottomTask);
    }

    // Atualizar o status da tarefa no localStorage após mover
    const taskId = curTask.getAttribute("data-id");
    const status = zone.id.replace("-lane", "");
    updateTaskStatus(taskId, status);
  });
});

const insertAboveTask = (zone, mouseY) => {
  const els = zone.querySelectorAll(".task:not(.is-dragging)");

  let closestTask = null;
  let closestOffset = Number.NEGATIVE_INFINITY;

  els.forEach((task) => {
    const { top } = task.getBoundingClientRect();

    const offset = mouseY - top;

    if (offset < 0 && offset > closestOffset) {
      closestOffset = offset;
      closestTask = task;
    }
  });

  return closestTask;
};

// Função para atualizar o status da tarefa no localStorage
function updateTaskStatus(taskId, status) {
  const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  const taskIndex = tasks.findIndex(task => task.text === taskId);
  if (taskIndex !== -1) {
    tasks[taskIndex].status = status;
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }
}