//Icono eliminar
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

//listar los clientes
// fetch('/Cliente')
//     .then(response => response.json())
//     .then(data => {
//         const clientesList = document.getElementById('clientesList');
//         data.forEach(cliente => {
//             const row = document.createElement('tr');
//             row.innerHTML = `
//                 <td><input type="checkbox"></td>
//                 <td>${cliente.id}</td>
//                 <td>${cliente.nombre} ${cliente.apellido}</td>
//                 <td>${cliente.identificacion}</td>
//                 <td>${cliente.telefono}</td>
//                 <td>${cliente.direccion}</td>
//                 <td><i class="fa fa-trash"></i></td>
//                 `;
//             clientesList.appendChild(row);
//         });
//     })
//     .catch(error => console.error('Error fetching clients:', error)); 




    //checkbox
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

//listar clientes
function cargarListaClientes() {
    fetch('/Cliente')  // Asegúrate de que esta URL sea correcta
        .then(response => response.json())  // Convertimos la respuesta a JSON
        .then(data => {
            const clientesList = document.getElementById('clientesList');
            clientesList.innerHTML = ''; // Limpiar cualquier contenido anterior

            // Recorrer los clientes y agregar las filas a la tabla
            data.forEach(cliente => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><input type="checkbox"></td>
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre} ${cliente.apellido}</td>
                    <td>${cliente.identificacion}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.direccion}</td>
                    <td><i class="fa fa-trash"></i></td>
                `;
                clientesList.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener los clientes:', error));
}

// Llamar a cargarListaClientes cuando la página se carga para mostrar los datos
window.onload = cargarListaClientes;

// Asociar la función al evento click del botón para actualizar la lista
document.getElementById('btn-actualizar').addEventListener('click', cargarListaClientes);
