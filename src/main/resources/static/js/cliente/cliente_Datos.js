function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
}
document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});
document.getElementById('btnCargar').addEventListener('click', function () {
    // Obtén el User-Id de las cookies
    const userId = getCookie('User-Id'); // Asegúrate de usar el nombre exacto de la cookie

    if (!userId) {
        alert("No se encontró la cookie 'User-Id'.");
        return;
    }

    // Realiza una solicitud al backend para obtener la información del usuario
    fetch(`/Cliente/${userId}`) // Ruta basada en el User-Id
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudo cargar la información del usuario.");
            }
            return response.json();
        })
        .then(data => {
            // Actualiza los campos del formulario con la información recibida
            document.getElementById('nombreCliente').value = data.nombre;
            document.getElementById('apellidoCliente').value = data.apellido;
            document.getElementById('num').value = data.identificacion;
            document.getElementById('correo').value = data.correo;
            document.getElementById('texto').value = data.direccion;
        })
        .catch(error => {
            console.error(error);
            alert("Ocurrió un error al cargar los datos del usuario.");
        });
});


document.getElementById('btnActualizar').addEventListener('click', function () {
    // Obtén los datos del formulario
    const userId = getCookie('User-Id'); // La cookie debe contener el ID del usuario

    if (!userId) {
        alert("No se encontró la cookie 'User-Id'.");
        return;
    }

    const updatedData = {
        nombre: document.getElementById('nombreCliente').value,
        apellido: document.getElementById('apellidoCliente').value,
        identificacion: document.getElementById('num').value,
        telefono: document.getElementById('telefono').value,
        correo: document.getElementById('correo').value,
        direccion: document.getElementById('texto').value
    };

    // Realiza una solicitud PUT al backend
    fetch(`/Cliente/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("No se pudo actualizar la información del usuario.");
        }
        return response.json();
    })
    .then(data => {
        alert("Datos actualizados correctamente.");
    })
    .catch(error => {
        console.error(error);
        alert("Ocurrió un error al actualizar los datos.");
    });
});