function enableBtn() {
    const dueDateValue = document.getElementById('dueDate').value;
    const message = document.getElementById('message');

    if (!dueDateValue) {
        message.style.display = 'block';
        message.textContent = 'Please select a due date.';
        return;
    }

    const dueDate = new Date(dueDateValue);
    const current = new Date();

    if (dueDate < current) {
        message.style.display = 'block';
        message.textContent = 'Due date must not be before the current date and time.';
    } else {
        message.style.display = 'none';
    }
}

function formatDate(date) {
    // Ensure date is a Date object
    if (!(date instanceof Date)) {
        date = new Date(date);
    }

    // Extract hours, minutes, day, month, and year
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based
    const year = date.getFullYear();

    // Format the date into "HH:MM - DD/MM/YYYY"
    return `${hours}:${minutes} - ${day}/${month}/${year}`;
}

document.getElementById('todoForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const totalTodos = document.getElementById('totalTodos');
    let newTodoId = parseInt(totalTodos.textContent, 10);
    newTodoId += 1;

    const formData = {
        id: newTodoId,
        goal: document.getElementById('goal').value,
        createDate: formatDate(new Date()),
        dueDate: formatDate(document.getElementById('dueDate').value),
        completed: 0
    };
    fetch('/todos', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    }).then(response => {
        if (response.status === 204) {
            alert('Todo added successfully');
        } else {
            alert('Error updating todo');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
});