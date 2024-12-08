// document.getElementById("btn-cotizar").addEventListener("click", function () {
// });
document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});
document.addEventListener("DOMContentLoaded", function () {
    fetch('/productos')
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener los productos");
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.querySelector("tbody");
            tbody.innerHTML = ""; // Limpia la tabla antes de cargar los datos
            data.forEach((producto, index) => {
                const row = `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.sku}</td>
                        <td><img src="${producto.imagen}" alt="${producto.nombre}" class="logo"></td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        })
        .catch(error => console.error(error));
});
