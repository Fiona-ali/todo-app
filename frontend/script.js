// ---------- State ----------
let tasks = []; // each task: { id, text, completed }
let nextId = 1;

// ---------- DOM Elements ----------
const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const taskList = document.getElementById("taskList");
const emptyMessage = document.getElementById("emptyMessage");

// ---------- Render Function ----------
// Clears the list and redraws it based on the current `tasks` array
function renderTasks() {
  taskList.innerHTML = "";

  if (tasks.length === 0) {
    emptyMessage.classList.remove("hidden");
  } else {
    emptyMessage.classList.add("hidden");
  }

  tasks.forEach((task) => {
    const li = document.createElement("li");
    li.className = "task-item" + (task.completed ? " completed" : "");
    li.dataset.id = task.id;

    li.innerHTML = `
      <div class="task-left">
        <input type="checkbox" class="task-checkbox" ${task.completed ? "checked" : ""} />
        <span class="task-text">${escapeHtml(task.text)}</span>
      </div>
      <button class="delete-btn">Delete</button>
    `;

    taskList.appendChild(li);
  });
}

// Prevents HTML injection if someone types tags into the input
function escapeHtml(str) {
  const div = document.createElement("div");
  div.textContent = str;
  return div.innerHTML;
}

// ---------- Add Task ----------
function addTask() {
  const text = taskInput.value.trim();

  if (text === "") {
    taskInput.focus();
    return;
  }

  tasks.push({
    id: nextId++,
    text: text,
    completed: false,
  });

  taskInput.value = "";
  taskInput.focus();
  renderTasks();
}

addBtn.addEventListener("click", addTask);

// Allow pressing Enter to add a task
taskInput.addEventListener("keydown", (e) => {
  if (e.key === "Enter") {
    addTask();
  }
});

// ---------- Delete & Complete (event delegation) ----------
// Instead of adding a listener to every task, we listen once on the list
// and figure out which task was clicked. This also works for tasks added later.
taskList.addEventListener("click", (e) => {
  const li = e.target.closest(".task-item");
  if (!li) return;

  const id = Number(li.dataset.id);

  if (e.target.classList.contains("delete-btn")) {
    tasks = tasks.filter((task) => task.id !== id);
    renderTasks();
  }

  if (e.target.classList.contains("task-checkbox")) {
    const task = tasks.find((task) => task.id === id);
    if (task) {
      task.completed = e.target.checked;
      renderTasks();
    }
  }
});

// ---------- Initial Render ----------
renderTasks();