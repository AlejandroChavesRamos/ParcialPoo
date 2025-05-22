/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

/**
 *
 * @author alejo
 */
public abstract class Status {
    //Respuesta Exitosa
    public static final int Ok = 200;
    public static final int Created = 201;
    public static final int No_Content = 204;
    
    //Error del cliente
    public static final int Bad_Request = 400;
    public static final int Not_Found = 404;
    
    //Error de respuesta del servidor
    public static final int Internal_Server_Error = 500;
    public static final int Not_Implemented = 501;
}
