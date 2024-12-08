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

document.getElementById("btn-descargar").addEventListener("click", function () {
});

document.getElementById("btn-actualizar").addEventListener("click", function () {
});
document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});
