/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.servicios;

import com.example.noticiero.entidades.Usuario;
import com.example.noticiero.enumeraciones.Rol;
import com.example.noticiero.excepciones.MiException;
import com.example.noticiero.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service

public class UsuarioServicio implements UserDetailsService{
    @Autowired
    private UsuarioRepositorio ur;
    @Transactional
    public void registrarUsuario(String nombre, String email, String password, String password2 ) throws MiException{
        validar(nombre, email, password, password2);
        Usuario u1 = new Usuario();
        u1.setNombre(nombre);
        u1.setEmail(email);
        u1.setPassword(password);
        u1.setRol(Rol.USER);
        ur.save(u1);
        
        
    }
    public void validar(String nombre, String email, String password, String password2 ) throws MiException{
      
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede ser nulo");
        }
        if(email.isEmpty() || email == null){
            throw new MiException("El email no puede ser nulo");
        }
         if(password.isEmpty() || password == null || password.length() <= 5){
            throw new MiException("La contraseña no puede ser nula, ni menor de 6 caracteres ");
        }
          if(!password.equals(password2)){
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    @Override
    //Para autenticar usuarios
    //Esto determina que spring security otrogue los permisos de usuario
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u1 = ur.buscarPorEmail(email);
        if(u1 != null){
            //Para la autorizacion de accesos por roles se crea una lista con todos los permisos
            //de esta manera seleccionando quien tiene permisos de admin o no, asi mismo se crea un 
            //objeto del tipo GrantedAuthority con SimpleGrantedList para especificar el rol y el metodo
            //pueda determinar is tiene autorizacion o no.
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+u1.getRol().toString()); // ROLE_USER
            permisos.add(p);
            //Se añade a la lista el nuevo objeto de autorizaciones y se retorna al usuario
           return new User(u1.getEmail(), u1.getPassword(), permisos );
        }else{
            return null;
        }
    }
    
}
