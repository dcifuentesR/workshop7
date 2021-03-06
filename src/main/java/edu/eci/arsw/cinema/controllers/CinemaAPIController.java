/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@RestController
public class CinemaAPIController {
    
    @Autowired
    CinemaServices cs;
    
    
    //--------------------GET------------------------------------
    @RequestMapping(value="/cinemas",method = RequestMethod.GET)
    public ResponseEntity manejadorGetAllCinemas(){
        try {
            return new ResponseEntity(cs.getAllCinemas(), HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("No se encontraron los cinemas",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/cinemas/{name}",method = RequestMethod.GET)
    public ResponseEntity manejadorGetCinemaByName(@PathVariable("name") String name){
        try {
            return new ResponseEntity(cs.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (CinemaException | CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("No se encontro ningun cinema con el nombre: "+name,HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "cinemas/{name}/{date}",method = RequestMethod.GET)
    public ResponseEntity manejadorGetFunctionsByCinemaAndDate(@PathVariable("name") String name,@PathVariable("date") String date){
        return new ResponseEntity(cs.getFunctionsbyCinemaAndDate(name, date),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "cinemas/{name}/{date}/{moviename}",method = RequestMethod.GET)
    public ResponseEntity manejadorGetFunction(@PathVariable("name") String name,@PathVariable("date") String date,@PathVariable("moviename") String movieName){
        try {
            return new ResponseEntity(cs.getFunction(name, date, movieName),HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("No se encontro ningun cinema con el nombre: "+name,HttpStatus.NOT_FOUND);
        }
    }
    
    //-------------------------POST-------------------------------
    @RequestMapping(value = "cinemas/{name}",method = RequestMethod.POST)
    public ResponseEntity manejadorPostCinema(@RequestBody Cinema cinema){
        //--falta mandar excepciones si el cinema no es válido
        cs.addNewCinema(cinema);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    //-------------------------PUT---------------------------------
    @RequestMapping(value = "cinemas/{name}", method =RequestMethod.PUT)
    public ResponseEntity manejadorPutFuncion(@RequestBody CinemaFunction funcion){
        
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
