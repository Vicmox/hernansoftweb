const apiUrl = "http://localhost:8080/mensajes";

// Función para obtener el valor de una cookie
function getCookie(name) {
    const cookies = document.cookie.split("; ");
    for (let cookie of cookies) {
        const [key, value] = cookie.split("=");
        if (key === name) {
            return value;
        }
    }
    return null;
}

// Función para listar los mensajes del usuario
async function listarMensajes() {
    const userId = getCookie("User-Id");
    if (!userId) {
        alert("No se encontró el identificador del usuario en las cookies.");
        return;
    }

    try {
        const response = await fetch(`${apiUrl}/usuario/mensajes/${userId}`);
        if (!response.ok) throw new Error("Error al obtener los mensajes.");

        const mensajes = await response.json();
        const tbody = document.getElementById("mensajeTableBody");
        tbody.innerHTML = "";
        mensajes.forEach(mensaje => {
            tbody.innerHTML += `
                <tr>
                    <td>${mensaje.idMensaje}</td>
                    <td>${mensaje.mensaje}</td>
                    <td>${mensaje.fecha}</td>
                    <td>${mensaje.estado}</td>
                    <td>${mensaje.nombreEmisor}</td>
                    <td>${mensaje.nombreDestinatario}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="eliminarMensaje(${mensaje.idMensaje})">Eliminar</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error(error.message);
        alert("Error al cargar los mensajes.");
    }
}

// Función para obtener los vendedores (usuarios con rol "Vendedor")
function obtenerVendedores() {
    fetch('/mensajes/usuarios/vendedores')
        .then(response => response.json())
        .then(vendedores => {
            const select = document.getElementById('destinatario');
            vendedores.forEach(vendedor => {
                const option = document.createElement('option');
                option.value = vendedor.idUsuario;  // Asumimos que 'idUsuario' es el campo de ID
                option.textContent = vendedor.nombre;  // Aquí puedes usar el nombre del vendedor
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener los vendedores:', error));
}

// Función para enviar un nuevo mensaje
async function enviarMensaje(event) {
    event.preventDefault();

    const mensaje = document.getElementById("mensaje").value;
    const destinatarioId = document.getElementById("destinatario").value;  // Aquí se obtiene el ID del destinatario (vendedor)

    if (!destinatarioId) {
        alert("Por favor, selecciona un vendedor.");
        return;
    }

    // Definir la fecha actual como la fecha de envío
    const fecha = new Date().toISOString().split('T')[0]; // Obtiene la fecha actual en formato YYYY-MM-DD

    // Definir el estado como "Enviado"
    const estado = "Enviado";

    const identificacion = getCookie("User-Id");
    if (!identificacion) {
        alert("No se encontró el identificador del usuario en las cookies.");
        return;
    }

    // Crear el objeto mensajeDTO con el ID del destinatario directamente
    const mensajeDTO = {
        identificacion: identificacion,
        mensaje: mensaje,
        destinatarioId: destinatarioId,
        fecha: fecha,
        estado: estado
    };

    try {
        const enviarResponse = await fetch(`${apiUrl}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(mensajeDTO),
        });

        if (!enviarResponse.ok) throw new Error("Error al enviar el mensaje.");
        alert("Mensaje enviado correctamente.");
        listarMensajes();
    } catch (error) {
        console.error(error.message);
        alert("Error al enviar el mensaje.");
    }
}

// Inicializar la funcionalidad
document.getElementById("mensajeForm").addEventListener("submit", enviarMensaje);
document.addEventListener("DOMContentLoaded", function () {
    listarMensajes();
    obtenerVendedores(); // Cargar los vendedores cuando la página se carga
});
