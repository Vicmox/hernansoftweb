package com.back_hernansoft.back_hernansoft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.back_hernansoft.back_hernansoft.config.CustomUserDetails;
import com.back_hernansoft.back_hernansoft.entity.pedido;
import com.back_hernansoft.back_hernansoft.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.service.UsuarioService;

@Controller
// @RequestMapping("/auth")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    // Agregar usuario
    @RequestMapping(value = "/manipuleUsuario", method = RequestMethod.POST, params = "Agregar")
    public String insertarUsuario(@ModelAttribute usuario usuario) {
        // Imprimir datos del usuario (debugging)
        // System.out.println(usuario.getIdUsuario());
        // System.out.println(usuario.getNombre());
        // System.out.println(usuario.getApellido());
        // System.out.println(usuario.getIdentificacion() +" que es de tipo
        // "+usuario.getTipoIdentificacion() );
        // System.out.println(usuario.getTelefono());
        // System.out.println(usuario.getCorreo());
        // System.out.println(usuario.getContrasena());
        // System.out.println(usuario.getRol());
        // System.out.println(" ");

        // Agrego esta línea para ver si se están obteniendo los usuarios
        // List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        // System.out.println("Usuarios desde el controlador: " + usuarios);

        // Verificar si el usuario ya existe por identificación
        Optional<usuario> existingUsuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (existingUsuario.isPresent()) {
            System.err.println("El usuario con identificación " + usuario.getIdentificacion() + " ya existe.");
            return "redirect:/admin_Gestion_Roles"; // Redirige si el usuario ya existe
        }

        // Si no existe, inserta el nuevo usuario
        usuario.setIdUsuario(null); // Asegúrate de que el id no se establezca manualmente
        usuarioService.insertar(usuario);

        return "redirect:/admin_Gestion_Roles"; // Redirigir al controlador de vistas adminusuarios después de agregarlo
    }

    // Editar usuario
    @RequestMapping(value = "/manipuleUsuario", method = RequestMethod.POST, params = "Editar")
    public String editarUsuario(@ModelAttribute usuario usuario) {
        // Imprimir datos del usuario (debugging)
        // System.out.println(usuario.getIdUsuario());
        // System.out.println(usuario.getNombre());
        // System.out.println(usuario.getApellido());
        // System.out.println(usuario.getIdentificacion() +" que es de tipo
        // "+usuario.getTipoIdentificacion() );
        // System.out.println(usuario.getTelefono());
        // System.out.println(usuario.getCorreo());
        // System.out.println(usuario.getContrasena());
        // System.out.println(usuario.getRol());
        // System.out.println(" ");

        // Agrego esta línea para ver si se están obteniendo los usuarios
        // List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        // System.out.println("Usuarios desde el controlador: " + usuarios);

        // Verificar si el usuario ya existe por identificación
        Optional<usuario> existingUsuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (existingUsuario.isPresent()) {
            usuario encontrado = existingUsuario.get(); // Aquí tienes el usuario completo
            System.err.println("El usuario con identificación " + usuario.getIdentificacion() + " ya existe y su ID es "
                    + encontrado.getIdUsuario());

            // Lógica para actualizar si aplica
            usuario.setIdUsuario(encontrado.getIdUsuario()); // Si necesitas reutilizar el ID
            usuarioService.update(usuario);
            return "redirect:/admin_Gestion_Roles"; // Redirige si el usuario ya existe
        } else {
            // Si no existe, inserta el nuevo usuario
            System.err.println("Cliente no existe");
            return "redirect:/admin_Gestion_Roles"; // Redirige si el usuario no existe
        }
    }

    // Eliminar Usuario
    @RequestMapping(value = "/deleteUsuario/{id}", method = RequestMethod.GET)
    public String eliminarUsuario(@PathVariable Integer id) {
        // System.err.println("ENTRO A deleteUsuario");
        System.err.println("Se borrara el usuario con identificación " + id);
        usuarioService.delete(id);
        return "redirect:/admin_Gestion_Roles"; // Redirige si el usuario ya existe AL ENDPOINT
    }

    // Endpoint para buscar un usuario por identificación
    @GetMapping("/buscarUsuario")
    public String buscarUsuario(@RequestParam("identificacionBuscar") String identificacion, Model model) {
        Optional<usuario> usuarioBuscado = usuarioService.findByIdentificacion(identificacion);

        if (usuarioBuscado.isPresent()) {
            model.addAttribute("usuario", usuarioBuscado.get()); // Cargar los datos del usuario encontrado en el
                                                                 // formulario
        } else {
            model.addAttribute("error", "Usuario no encontrado");
        }

        // Mostrar todos los usuarios en la vista, o podrías redirigir si prefieres solo
        // mostrar el formulario de edición
        model.addAttribute("usuarios", usuarioService.obtenerTodosUsuarios());
        return "Admin/admin_Gestion_Roles"; // Asegúrate de que este sea el nombre de la vista que muestra el formulario de
                                // edición
    }


    @GetMapping("/admin_Gestion_Roles")
    public String admin_Gestion_Roles(Model model) {
        System.err.println("ENTRO A @GetMapping /admin_Gestion_Roles ");

        // Obtener lista de usuarios
        List<usuario> usuarios= usuarioService.obtenerTodosUsuarios();
        usuario usuario= new usuario();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("usuario",usuario);

        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Obtener el usuario completo basado en el nombre o identificador del usuario
            Optional<usuario> usuarioActual = usuarioService.findByIdentificacion(userDetails.getIdentificacion());

            // Pasar detalles del usuario al modelo
            model.addAttribute("usuarioActual", usuarioActual);
            model.addAttribute("nombre", userDetails.getNombre());
            model.addAttribute("apellido", userDetails.getApellido());
            model.addAttribute("rol", userDetails.getRol());
            model.addAttribute("identificacion", userDetails.getIdentificacion());
            model.addAttribute("correo", userDetails.getCorreo());
            model.addAttribute("direccion", userDetails.getDireccion());
        } else {
            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
        }

        return "Admin/admin_Gestion_Roles"; // Nombre de la plantilla (archivo .html en templates)
        //return "redirect:/adminusuarios";
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //SE USA EN Admin/adminPerfil
    @GetMapping("/adminUsuarios")
    public String adminUsuarios() {
        System.err.println("ENTRO A @GetMapping /adminUsuarios ");
        return "Admin/adminUsuarios"; // Nombre de la plantilla (archivo .html en templates)
    }

    // Editar usuario
    @RequestMapping(value = "/editarPerfilAdmin", method = RequestMethod.POST, params = "Editar")
    public String editarPerfilAdmin(@ModelAttribute usuario usuario) {
        System.err.println("ENDPOINT EDITAR PERFIL ADMIN");

        // Verificar si el usuario ya existe por identificación
        Optional<usuario> existingUsuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (existingUsuario.isPresent()) {
            usuario encontrado = existingUsuario.get(); // Aquí tienes el usuario completo
            System.err.println("El usuario con identificación " + usuario.getIdentificacion() + " ya existe y su ID es "
                    + encontrado.getIdUsuario());

            // Lógica para actualizar si aplica
            usuario.setIdUsuario(encontrado.getIdUsuario()); // Si necesitas reutilizar el ID
            usuarioService.update(usuario);
            return "redirect:/adminusuarios"; // Redirige si el usuario ya existe
        } else {
            // Si no existe, inserta el nuevo usuario
            System.err.println("Cliente no existe");
            return "redirect:/adminusuarios"; // Redirige si el usuario no existe
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SE USA EN Admin/admin_Gestion_Clientes
    @GetMapping("/admin_Gestion_Clientes")
    public String admin_Gestion_Clientes(Model model) {
       System.err.println("ENTRO A @GetMapping /admin_Gestion_Roles ");

        // Obtener lista de usuarios
        List<usuario> usuarios= usuarioService.obtenerTodosUsuarios();

        // Crear una nueva lista para usuarios con rol "cliente"
        List<usuario> usuariosClientes = new ArrayList<>();

        // Filtrar usuarios con rol "cliente"
        for (usuario u : usuarios) {
            if ("cliente".equals(u.getRol())) {
                usuariosClientes.add(u);
            }
        }

        // Agregar la lista de usuarios clientes al modelo
        model.addAttribute("usuariosClientes", usuariosClientes);
        //model.addAttribute("usuarios",usuarios);

        // Crear un nuevo objeto usuario vacío para posibles formularios
        usuario usuario = new usuario();
        model.addAttribute("usuario", usuario);

//        // Obtener la autenticación actual
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getPrincipal() instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//
//            // Obtener el usuario completo basado en el nombre o identificador del usuario
//            Optional<usuario> usuarioActual = usuarioService.findByIdentificacion(userDetails.getIdentificacion());
//
//            // Pasar detalles del usuario al modelo
//            model.addAttribute("usuarioActual", usuarioActual);
//            model.addAttribute("nombre", userDetails.getNombre());
//            model.addAttribute("apellido", userDetails.getApellido());
//            model.addAttribute("rol", userDetails.getRol());
//            model.addAttribute("identificacion", userDetails.getIdentificacion());
//            model.addAttribute("correo", userDetails.getCorreo());
//            model.addAttribute("direccion", userDetails.getDireccion());
//        } else {
//            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
//        }

        return "Admin/admin_Gestion_Clientes"; // Nombre de la plantilla (archivo .html en templates)
    }

    // Endpoint para buscar un usuario por identificación
    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam("identificacionBuscar") String identificacion, Model model) {
        // Obtener lista de usuarios
        List<usuario> usuarios= usuarioService.obtenerTodosUsuarios();

        // Crear una nueva lista para usuarios con rol "cliente"
        List<usuario> usuariosClientes = new ArrayList<>();

        // Filtrar usuarios con rol "cliente"
        for (usuario u : usuarios) {
            if ("cliente".equals(u.getRol())) {
                usuariosClientes.add(u);
            }
        }

        // Agregar la lista de usuarios clientes al modelo
        model.addAttribute("usuariosClientes", usuariosClientes);
        //model.addAttribute("usuarios",usuarios);

        // Crear un nuevo objeto usuario vacío para posibles formularios
        usuario usuario = new usuario();
        //model.addAttribute("usuario", usuario);

        Optional<usuario> usuarioBuscado = usuarioService.findByIdentificacion(identificacion);

        if (usuarioBuscado.isPresent()) {
            model.addAttribute("usuario", usuarioBuscado.get()); // Cargar los datos del usuario encontrado en el
            // formulario
        } else {
            model.addAttribute("error", "Usuario no encontrado");
        }

        // Mostrar todos los usuarios en la vista, o podrías redirigir si prefieres solo
        // mostrar el formulario de edición
        //model.addAttribute("usuarios", usuarioService.obtenerTodosUsuarios());
        return "Admin/admin_Gestion_Clientes"; // Asegúrate de que este sea el nombre de la vista que muestra el formulario de
        // edición
    }

    // Agregar Cliente
    @RequestMapping(value = "/manipuleCliente", method = RequestMethod.POST, params = "Agregar")
    public String insertarCliente(@ModelAttribute usuario usuario) {

        // Verificar si el usuario ya existe por identificación
        Optional<usuario> existingUsuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (existingUsuario.isPresent()) {
            System.err.println("El usuario con identificación " + usuario.getIdentificacion() + " ya existe.");
            return "redirect:/admin_Gestion_Clientes"; // Redirige si el usuario ya existe
        }

        // Si no existe, inserta el nuevo usuario
        usuario.setIdUsuario(null); // Asegúrate de que el id no se establezca manualmente
        usuario.setRol("cliente");  // Asegúrate de que el usuario siempre sea cliente
        usuarioService.insertar(usuario);

        return "redirect:/admin_Gestion_Clientes"; // Redirigir al controlador de vistas adminusuarios después de agregarlo
    }

    // Editar Cliente
    @RequestMapping(value = "/manipuleCliente", method = RequestMethod.POST, params = "Editar")
    public String editarCliente(@ModelAttribute usuario usuario) {
        // Imprimir datos del usuario (debugging)
        // System.out.println(usuario.getIdUsuario());
        // System.out.println(usuario.getNombre());
        // System.out.println(usuario.getApellido());
        // System.out.println(usuario.getIdentificacion() +" que es de tipo
        // "+usuario.getTipoIdentificacion() );
        // System.out.println(usuario.getTelefono());
        // System.out.println(usuario.getCorreo());
        // System.out.println(usuario.getContrasena());
        // System.out.println(usuario.getRol());
        // System.out.println(" ");

        // Agrego esta línea para ver si se están obteniendo los usuarios
        // List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        // System.out.println("Usuarios desde el controlador: " + usuarios);

        // Verificar si el usuario ya existe por identificación
        Optional<usuario> existingUsuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (existingUsuario.isPresent()) {
            usuario encontrado = existingUsuario.get(); // Aquí tienes el usuario completo
            System.err.println("El usuario con identificación " + usuario.getIdentificacion() + " ya existe y su ID es "
                    + encontrado.getIdUsuario());

            // Lógica para actualizar si aplica
            usuario.setIdUsuario(encontrado.getIdUsuario()); // Si necesitas reutilizar el ID
            usuarioService.update(usuario);
            return "redirect:/admin_Gestion_Clientes"; // Redirige si el usuario ya existe
        } else {
            // Si no existe, inserta el nuevo usuario
            System.err.println("Cliente no existe");
            return "redirect:/admin_Gestion_Clientes"; // Redirige si el usuario no existe
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SE USA EN Admin/admin_Cancelacion_Pedidos
    
    @GetMapping("/admin_Cancelacion_Pedidos")
    public String admin_Cancelacion_Pedidos(Model model) {
        System.err.println("ENTRO A @GetMapping /admin_Cancelacion_Pedidos ");

        // Obtener todos los pedidos
        List<pedido> pedidos = pedidoService.obtenerTodosPedidos(); // Asume que tienes un método en tu servicio de pedido
        System.err.println("Pedidos obtenidos: " + pedidos.size()); // Verificar el número de pedidos

        // Obtener todos los usuarios
        List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        //System.err.println("Usuarios obtenidos: " + usuarios.size()); // Verificar el número de usuarios

        // Llenar las variables auxiliares en cada pedido
        for (pedido p : pedidos) {
            System.err.println("Procesando pedido con ID: " + p.getIdPedido()); // Verificar el ID del pedido

            // Buscar el usuario cliente y vendedor según los idCliente y idVendedor
            Optional<usuario> usuarioCliente = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(p.getIdCliente()))
                    .findFirst();
            //System.err.println("Usuario cliente encontrado: " + (usuarioCliente.isPresent() ? usuarioCliente.get().getNombre() : "No encontrado"));

            Optional<usuario> usuarioVendedor = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(p.getIdVendedor()))
                    .findFirst();
            //System.err.println("Usuario vendedor encontrado: " + (usuarioVendedor.isPresent() ? usuarioVendedor.get().getNombre() : "No encontrado"));

            // Asignar los usuarios encontrados a las variables auxiliares
            usuarioCliente.ifPresent(u -> p.setUsuarioCliente(u));
            usuarioVendedor.ifPresent(u -> p.setUsuarioVendedor(u));
        }

        // Agregar las listas al modelo para que puedan ser mostradas en la vista
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("usuarios", usuarios);

        // Agregar un pedido vacío al modelo
        model.addAttribute("pedidoForm", new pedido());

        return "Admin/admin_Cancelacion_Pedidos"; // Nombre de la plantilla (archivo .html en templates)
    }

    @GetMapping("/buscarPedido")
    public String buscarPedidoEstado(@RequestParam("idPedido") String idPedido, Model model) {
        System.err.println("ENTRO A @GetMapping /buscarPedido ");
        pedido pedido3 = new pedido();

        // Obtener todos los pedidos
        List<pedido> pedidos = pedidoService.obtenerTodosPedidos(); // Asume que tienes un método en tu servicio de pedido
        System.err.println("Pedidos obtenidos: " + pedidos.size()); // Verificar el número de pedidos

        // Obtener todos los usuarios
        List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        //System.err.println("Usuarios obtenidos: " + usuarios.size()); // Verificar el número de usuarios

        // Llenar las variables auxiliares en cada pedido
        for (pedido p : pedidos) {
            System.err.println("Procesando pedido con ID: " + p.getIdPedido()); // Verificar el ID del pedido

            // Buscar el usuario cliente y vendedor según los idCliente y idVendedor
            Optional<usuario> usuarioCliente = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(p.getIdCliente()))
                    .findFirst();

            Optional<usuario> usuarioVendedor = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(p.getIdVendedor()))
                    .findFirst();

            // Asignar los usuarios encontrados a las variables auxiliares
            usuarioCliente.ifPresent(u -> p.setUsuarioCliente(u));
            usuarioVendedor.ifPresent(u -> p.setUsuarioVendedor(u));

            //System.err.println("EL PEDIDO CONTIENE "+p);
            System.err.println("EL PEDIDO CONTIENE "+p.getIdPedido());
            System.err.println("EL PEDIDO SE COMPARA CON  "+idPedido);

            try {
                int idPedidoInt = Integer.parseInt(idPedido); // Convierte el String a int
                if (p.getIdPedido().equals(idPedidoInt)) {
                    pedido3 = p;
                    System.err.println("EL PEDIDO ALMACENADO CON ID" + pedido3.getIdPedido() + " ES " + pedido3.toString());
                }
            } catch (NumberFormatException e) {
                System.err.println("El valor de idPedido no es un número válido.");
            }



        }

        System.err.println("EL PEDIDO QUE SE MOSTRARA ES "+pedido3.toString());
        model.addAttribute("pedidoForm", pedido3); // Agregar solo el pedido encontrado al modelo
        // Agregar la lista completa de pedidos al modelo
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("usuarios", usuarios);

        return "Admin/admin_Cancelacion_Pedidos"; // Nombre de la plantilla (archivo .html en templates)
    }


    @PostMapping("/cambiarEstado")
    public String cambiarEstado(
            @RequestParam("estado") String estado,
            @RequestParam("idPedido") Integer idPedido,
            Model model) {
        System.err.println("ENTRO A @GetMapping /cambiarEstado ");

        // Obtener todos los pedidos
        List<pedido> pedidos = pedidoService.obtenerTodosPedidos();

        // Buscar el pedido por su ID
        pedido pedido = pedidos.stream()
                .filter(p -> p.getIdPedido().equals(idPedido))
                .findFirst()
                .orElse(null);

        if (pedido != null) {
            // Actualizar el estado del pedido
            pedido.setEstado(estado);
            System.err.println("Pedido con ID: " + idPedido+" ahora tiene estado "+estado);
            pedidoService.actualizarPedido(pedido); // Guardar cambios
        } else {
            // Manejar el caso en que el pedido no exista
            System.err.println("Pedido no encontrado con ID: " + idPedido);
        }

        // Volver a cargar la lista de pedidos para actualizar la vista
        model.addAttribute("pedidos", pedidoService.obtenerTodosPedidos());

        // Redireccionar a la vista actualizada (por ejemplo, lista de pedidos)
        return "redirect:/admin_Cancelacion_Pedidos";
    }

    // Editar Cliente
    @RequestMapping(value = "/editarPedido", method = RequestMethod.POST, params = "Editar")
    public String editarPedido(@ModelAttribute usuario usuario) {

        return "redirect:/admin_Cancelacion_Pedidos"; // Redirige si el usuario no existe

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // @GetMapping("/")
    // @PreAuthorize("permitAll()")
    // public String index(Model model) {
    // model.addAttribute("usuarios", usuarioService.obtenerTodosUsuarios());
    // return "index"; // Si estás usando Thymeleaf, la vista será 'index.html'
    // }
    // ESTE Y ABAJO SON LO MISMO PERO UNO ES PARA VER EN LA CONSOLA
    // @GetMapping("/")
    // @PreAuthorize("permitAll()")
    // public String index(Model model) {
    // List<usuario> usuarios = usuarioService.obtenerTodosUsuarios();
    // //System.out.println("Usuarios desde el controlador: " + usuarios); // Agrega
    // esta línea para ver si se están
    // // obteniendo los usuarios
    // model.addAttribute("usuarios", usuarios);
    // return "index"; // Si estás usando Thymeleaf, la vista será 'index.html'
    // }

    // @GetMapping("/")
    // @PreAuthorize("permitAll()")
    // public String pagPrincipal(Model model) {
    //
    // return "pagPrincipal"; // Si estás usando Thymeleaf, la vista será
    // 'index.html'
    // }
}