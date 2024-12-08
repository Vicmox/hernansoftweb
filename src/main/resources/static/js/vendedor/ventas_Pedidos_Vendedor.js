//Boton de limpiar
document.getElementById("btnlimpiar").addEventListener("click", function () {
    
    const inputsTexto = document.querySelectorAll("input[type='text'], input[type='email'], input[type='date']");
    inputsTexto.forEach(input => input.value = "");

    const selects = document.querySelectorAll("select");
    selects.forEach(select => select.selectedIndex = 0);

    const textareas = document.querySelectorAll("textarea");
    textareas.forEach(textarea => textarea.value = "");

    const checkboxes = document.querySelectorAll("input[type='checkbox'], input[type='radio']");
    checkboxes.forEach(checkbox => checkbox.checked = false);

    document.querySelector('#total').textContent = '0.00';
    document.querySelector('#saldo').textContent = '0.00';
    
});

//Icono de eliminar en la tabla
document.addEventListener("DOMContentLoaded", function() {
    const deleteIcons = document.querySelectorAll(".fa-trash");

    deleteIcons.forEach(icon => {
        icon.addEventListener("click", function() {
            const row = this.closest("tr");
            if (row) {
                row.remove();
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    // Checkboxes
    const checkboxes = document.querySelectorAll('table input[type="checkbox"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                checkboxes.forEach(cb => {
                    if (cb !== this) {
                        cb.checked = false;
                    }
                });
            }
        });
    });
});

document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});

document.getElementById("btn-registrar").addEventListener("click", function () {
});

document.getElementById("btn-imprimir").addEventListener("click", function () {
});