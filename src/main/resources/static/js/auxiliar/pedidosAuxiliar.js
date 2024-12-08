document.addEventListener("DOMContentLoaded", function () {
    // Cargar todos los pedidos al iniciar la página
    fetchPedidos();

    // Agregar funcionalidad al botón Visualizar
    const btnVisualizar = document.getElementById("btn-visualizar");
    btnVisualizar.addEventListener("click", visualizarPedido);
});

// Función para obtener y cargar los pedidos en la tabla
async function fetchPedidos() {
    try {
        const response = await fetch("/pedidos");
        if (response.ok) {
            const pedidos = await response.json();
            cargarPedidosEnTabla(pedidos);
        } else {
            console.error("Error al obtener los pedidos:", response.statusText);
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
    }
}
document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});
// Función para cargar los pedidos en la tabla HTML
function cargarPedidosEnTabla(pedidos) {
    const tbody = document.querySelector("table tbody");
    tbody.innerHTML = ""; // Limpiar contenido existente
    pedidos.forEach((pedido) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td><input type="checkbox" data-id="${pedido.idPedido}"></td>
            <td>${pedido.idPedido}</td>
            <td>${pedido.fecha_pedido}</td>
            <td>${pedido.cliente}</td>
            <td>
                <select class="form-select">
                    <option value="${pedido.estado}" selected>${pedido.estado}</option>
                    <option value="Atrasado">Atrasado</option>
                    <option value="Pagado">Pagado</option>
                    <option value="Pendiente">Pendiente</option>
                    <option value="En Proceso">En Proceso</option>
                </select>
            </td>
            <td>${pedido.total}</td>
            <td>${pedido.encargado}</td>
            <td><i class="fa fa-trash"></i></td>
        `;
        tbody.appendChild(row);
    });
}

// Función para obtener el pedido seleccionado y cargar su detalle
async function visualizarPedido() {
    const checkboxes = document.querySelectorAll("table tbody input[type='checkbox']");
    let pedidoSeleccionado = null;

    checkboxes.forEach((checkbox) => {
        if (checkbox.checked) {
            pedidoSeleccionado = checkbox.dataset.id; // Obtener el ID del pedido
        }
    });

    if (!pedidoSeleccionado) {
        alert("Por favor, seleccione un pedido para visualizar.");
        return;
    }

    try {
        const response = await fetch(`/pedidos/${pedidoSeleccionado}`);
        if (response.ok) {
            const pedido = await response.json();
            cargarDetallePedido(pedido);
        } else {
            console.error("Error al obtener el pedido:", response.statusText);
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
    }
}

// Función para cargar el detalle del pedido en el formulario
function cargarDetallePedido(pedido) {
    document.getElementById("noPedido").textContent = `Pedido No. ${pedido.id}`;
    document.getElementById("comprador").value = pedido.cliente;
    document.getElementById("correo").value = pedido.correo;
    document.getElementById("metodo_de_pago").value = pedido.metodo_pago;
    document.getElementById("Monto_abonado").value = pedido.monto_abonado;
    document.getElementById("numero_de_Cuotas").value = pedido.numero_cuotas;
    document.getElementById("fecha").value = pedido.fecha_facturacion;
    document.getElementById("direccion").value = pedido.direccion;
    document.getElementById("fecha_cobros").value = pedido.fecha_cobros;
    document.getElementById("mensaje").value = pedido.mensaje;
    document.getElementById("estado").value = pedido.estado;
    document.getElementById("total").textContent = pedido.total;
    document.getElementById("abonado").value = pedido.abonado;
    document.getElementById("saldo").value = pedido.saldo;
}