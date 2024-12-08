console.log("Hello from gerentePedido.js!"); 
 //Botón Eliminar
document.getElementById("btn-limpiar").addEventListener("click", function () {

    const inputsTexto = document.querySelectorAll("input[type='text'], input[type='email'], input[type='date']");
    inputsTexto.forEach(input => input.value = "");

    const selects = document.querySelectorAll("select");
    selects.forEach(select => select.selectedIndex = 0);

    const textareas = document.querySelectorAll("textarea");
    textareas.forEach(textarea => textarea.value = "");

    const checkboxes = document.querySelectorAll("input[type='checkbox'], input[type='radio']");
    checkboxes.forEach(checkbox => checkbox.checked = false);
});
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

    // Botón Visualizar
    document.getElementById("btn-visualizar").addEventListener("click", function () {
        const selectedRow = Array.from(checkboxes).find(cb => cb.checked)?.closest('tr');
        
        if (selectedRow) {
            const [_, noPedido, fecha, cliente, estado, total] = selectedRow.children;
            document.getElementById('noPedido').textContent = `Pedido No. ${noPedido.textContent.trim()}`;
            document.getElementById('comprador').value = cliente.textContent.trim();
            document.getElementById('fecha').value = formatFecha(fecha.textContent.trim());
            document.getElementById('terminos').value = estado.textContent.trim();
        } else {
            alert('Por favor seleccione un pedido antes de visualizar.');
        }
    });

    function formatFecha(fecha) {
        const [day, month, year] = fecha.split('/');
        return `${year}-${month}-${day}`; //YYYY-MM-DD
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.querySelector("#detalle-pedido tbody");
    const totalLabel = document.getElementById("total");
    const saldoInput = document.getElementById("saldo");
    const abonadoInput = document.getElementById("abonado");
    const compradorInput = document.getElementById("comprador");
    const correoInput = document.getElementById("correo");
    const fechaInput = document.getElementById("fecha");

    // Calcular monto de una fila
    function calcularMonto(row) {
        const cantidad = parseFloat(row.querySelector("#cantidad").value) || 0;
        const costoUnitario = parseFloat(row.querySelector("#costoUnitario").value) || 0;
        const montoCell = row.querySelector("td:nth-last-child(2)");
        const monto = cantidad * costoUnitario;
        montoCell.textContent = monto.toFixed(2);
        return monto;
    }

    // Calcular total de la tabla
    function calcularTotal() {
        let total = 0;
        const rows = tableBody.querySelectorAll("tr");
        rows.forEach(row => {
            total += calcularMonto(row);
        });
        totalLabel.textContent = total.toFixed(2);
        return total;
    }

    // Calcular saldo adeudado
    function calcularSaldo() {
        const total = parseFloat(totalLabel.textContent) || 0;
        const abonado = parseFloat(abonadoInput.value) || 0;
        const saldo = total - abonado;
        saldoInput.value = saldo.toFixed(2);
    }

    // Eventos para actualizar cálculos
    tableBody.addEventListener("input", function (event) {
        if (event.target.id === "cantidad" || event.target.id === "costoUnitario") {
            calcularTotal();
            calcularSaldo();
        }
    });

    abonadoInput.addEventListener("input", calcularSaldo);

    // Agregar nueva fila
    document.getElementById("btn-agregar-fila").addEventListener("click", function () {
        const nuevaFila = document.createElement("tr");
        nuevaFila.innerHTML = `
            <td>${tableBody.querySelectorAll("tr").length + 1}</td>
            <td>
                <select class="form-select">
                    <option value="productA">Producto A</option>
                    <option value="productB">Producto B</option>
                    <option value="productC">Producto C</option>
                    <option value="productD">Producto D</option>
                </select>
            </td>
            <td>SKU-${Math.floor(Math.random() * 1000)}</td>
            <td><textarea class="form-control" id="descripcion" rows="3"></textarea></td>
            <td><input type="text" class="form-control" id="cantidad"></td>
            <td><input type="text" class="form-control" id="costoUnitario"></td>
            <td>0.00</td>
            <td><i class="fa fa-trash" style="cursor: pointer;"></i></td>
        `;
        tableBody.appendChild(nuevaFila);
    });

    // Evento para eliminar filas dinámicas
    tableBody.addEventListener("click", function (event) {
        if (event.target.classList.contains("fa-trash")) {
            const row = event.target.closest("tr");
            if (row) {
                row.remove();
                calcularTotal();
                calcularSaldo();
            }
        }
    });

    document.getElementById("btn-imprimir").addEventListener("click", function () {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();
    
        // Ruta del logo
        const logoURL = "https://i.ibb.co/9GSBnvq/image.png";
    
        // Cargar el logo y generar el PDF
        const img = new Image();
        img.src = logoURL;
    
        img.onload = function () {
            // Encabezado
            doc.addImage(img, "PNG", 10, 10, 30, 30); // Logo
            doc.setFontSize(16);
            doc.text("Detalles del Pedido", 105, 20, null, null, "center");
            doc.setFontSize(12);
            doc.text(`Fecha Facturación: ${fechaInput.value || "N/A"}`, 50, 30); // Fecha junto al logo
    
            // Información del cliente
            const startX = 10;
            const startY = 50;
            doc.text(`Comprador: ${compradorInput.value || "N/A"}`, startX, startY);
            doc.text(`Correo: ${correoInput.value || "N/A"}`, startX, startY + 10);
            doc.text(`Dirección: ${document.getElementById("direccion").value || "N/A"}`, startX, startY + 20);
            doc.text(`Cuotas: ${document.getElementById("cuotas").value || "N/A"}`, startX, startY + 30);
    
            // Tabla de productos
            let tableStartY = startY + 40;
            doc.autoTable({
                head: [["#", "Producto/Servicio", "Código", "Descripción", "Cantidad", "Costo Unitario", "Monto"]],
                body: Array.from(tableBody.querySelectorAll("tr")).map(row => {
                    const cells = row.querySelectorAll("td");
                    return [
                        cells[0].textContent, // #
                        cells[1].querySelector("select").value, // Producto/Servicio
                        cells[2].textContent, // Código
                        cells[3].querySelector("textarea").value, // Descripción
                        cells[4].querySelector("input").value, // Cantidad
                        cells[5].querySelector("input").value, // Costo Unitario
                        cells[6].textContent, // Monto
                    ];
                }),
                startY: tableStartY,
                theme: "grid", // Estilo con líneas de tabla
                styles: { fontSize: 10 }, // Tamaño de letra para la tabla
            });
    
            // Totales
            const total = parseFloat(totalLabel.textContent) || 0;
            const abonado = parseFloat(abonadoInput.value) || 0;
            const saldo = parseFloat(saldoInput.value) || 0;
    
            const totalStartY = doc.lastAutoTable.finalY + 10; // Posición después de la tabla
            doc.setFontSize(12);
            doc.text(`Total: ${total.toFixed(2)}`, startX, totalStartY);
            doc.text(`Abonado: ${abonado.toFixed(2)}`, startX, totalStartY + 10);
            doc.text(`Saldo Adeudado: ${saldo.toFixed(2)}`, startX, totalStartY + 20);
    
            // Guardar PDF
            doc.save("pedido.pdf");
        };
    
        img.onerror = function () {
            console.error("No se pudo cargar el logo.");
        };
    });
    

});


document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault(); // Detiene el envío del formulario
});

document.getElementById("btn-registrar").addEventListener("click", function () {
});

document.getElementById("btn-imprimir").addEventListener("click", function () {
});


