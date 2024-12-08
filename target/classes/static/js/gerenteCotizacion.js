
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
                const [_, idFactura, fecha, idCliente] = selectedRow.children;
                document.getElementById('noCotiz').textContent = `Cotización No. ${idFactura.textContent.trim()}`;
                document.getElementById('idFactura').textContent = idFactura.textContent.trim();
                document.getElementById('idCliente').value = idCliente.textContent.trim();
            } else {
                alert('Por favor seleccione un pedido antes de visualizar.');
            }
        });
    
        function formatFecha(fecha) {
            const [day, month, year] = fecha.split('/');
            return `${year}-${month}-${day}`; //YYYY-MM-DD
        }
    });
    
    document.getElementById("btn-descargar").addEventListener("click", function () {
    });
    
    document.getElementById("btn-facturar").addEventListener("click", function () {
    });

    document.getElementById("btn-actualizar").addEventListener("click", function () {
    });