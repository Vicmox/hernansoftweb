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

//--------------------------------------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------------------------------

// Boton Cerrar sesión
document.getElementById('logoutButton').addEventListener('click', function () {
    alert('Cerrando sesión...');
    // Aquí puedes redirigir o manejar la acción de logout
    window.location.href = '/logout';
});
//----------------------------------------------------------------------------------------------------------------------

// Boton de Registrar
// Espera a que el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', () => {
    // Captura el botón por su ID
    const btnRegistrar = document.getElementById('btnRegistrar');

    // Escucha el evento 'click' del botón
    btnRegistrar.addEventListener('click', (event) => {
        event.preventDefault(); // Evita la acción predeterminada del botón (si fuera necesario)

        // Captura los valores de los campos del formulario
        const formDatos = {
            cliente: document.getElementById('cliente').value.trim(),
            correo: document.getElementById('correo').value.trim(),
            direccion: document.getElementById('direccion').value.trim(),
            fechaCobros: document.getElementById('fecha_cobros').value,
            metodoDePago: document.querySelector('select.form-select').value,
            fechaFacturacion: document.getElementById('fecha').value
        };

        // Valida que todos los campos requeridos tengan valores
        if (!formDatos.cliente || !formDatos.correo || !formDatos.direccion || !formDatos.fechaCobros || !formDatos.metodoDePago || !formDatos.fechaFacturacion) {
            alert('Por favor, complete todos los campos antes de registrar.');
            return;
        }

        // Captura los valores de cada fila en la tabla
        const table = document.getElementById('product-table');
        const rows = table.querySelectorAll('tbody tr'); // Captura las filas del cuerpo de la tabla
        const tableData = [];

        rows.forEach((row, index) => {
            const producto = row.querySelector('select').value;
            const sku = row.cells[2].textContent.trim();
            const mensaje = row.querySelector('textarea').value.trim();
            const cantidad = row.querySelector('#cantidad').value.trim();
            const valorUnitario = row.querySelector('#valor_unitario').value.trim();

            if (!producto || !cantidad || !valorUnitario) {
                alert(`Por favor, complete todos los campos en la fila ${index + 1}.`);
                return;
            }

            tableData.push({
                producto,
                sku,
                mensaje,
                cantidad,
                valorUnitario,
            });
        });
        // Si no hay datos en la tabla o no se completaron correctamente, se detiene el registro
        if (tableData.length === 0) {
            alert('Por favor, complete los datos de la tabla antes de registrar.');
            return;
        }
        // Captura los valores adicionales
        const additionalData = {
            mensaje: document.getElementById('mensaje').value.trim(),
            total: document.getElementById('total').textContent.trim(),
            abonado: document.getElementById('abonado').value.trim(),
            saldo: document.getElementById('saldo').textContent.trim()
        };
        // Validación adicional
        if (!additionalData.mensaje || !additionalData.abonado) {
            alert('Por favor, complete los campos adicionales antes de registrar.');
            return;
        }
         // Muestra los datos capturados en la consola
        console.log('Datos del formulario:', formDatos);
        console.log('Datos de la tabla:', tableData);
        console.log('Datos adicionales:', additionalData);
         // Limpia los inputs del formulario, tabla y secciones adicionales
        document.getElementById('billing-form').reset();
        rows.forEach((row) => {
            row.querySelector('select').value = '';
            row.querySelector('textarea').value = '';
            row.querySelector('#cantidad').value = '';
            row.querySelector('#valor_unitario').value = '';
        });
        document.getElementById('mensaje').value = '';
        document.getElementById('abonado').value = '';
        // Mensaje de confirmación
        alert('Datos registrados con éxito.');
    });

    btnImprimir.addEventListener('click', () => {
        window.print(); // Abre el cuadro de diálogo de impresión
    });
});
