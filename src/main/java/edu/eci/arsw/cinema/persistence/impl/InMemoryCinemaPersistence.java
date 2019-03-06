/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component("cps")
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        
        String functionDate2 = "2018-01-20 15:30";
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct21 = new CinemaFunction(new Movie("Romantic comedy","Comedy"),functionDate2);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night","Horror"),functionDate2);
        functions2.add(funct21);
        functions2.add(funct22);
        Cinema c2=new Cinema("cinemaXX",functions2);
        
        String functionDate3 = "2019-06-18 15:30";
        List<CinemaFunction> functions3= new ArrayList<>();
        CinemaFunction funct31 = new CinemaFunction(new Movie("Fifty Shades of Grey","Garbage"),functionDate3);
        CinemaFunction funct32 = new CinemaFunction(new Movie("Dead Poet Society","Drama"),functionDate3);
        functions3.add(funct31);
        functions3.add(funct32);
        Cinema c3=new Cinema("cinemaXXX",functions3);
        
        
        cinemas.put("cinemaX", c);
        cinemas.put("cinemaXX", c2);
        cinemas.put("cinemaXXX", c3);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        List<CinemaFunction> funciones=getFunctionsbyCinemaAndDate(cinema, date);
        for(CinemaFunction f:funciones)
            if(f.getMovie().getName().equals(movieName))
                f.buyTicket(row, col);
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> funciones=cinemas.get(cinema).getFunctions();
        for(CinemaFunction f:funciones)
            if(!f.getDate().equals(date))
                funciones.remove(f);
        return funciones;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }

    @Override
    public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
        
        return new HashSet(cinemas.values());
    }

    @Override
    public CinemaFunction getFunction(String cinema, String date, String movieName) throws CinemaPersistenceException{
        List<CinemaFunction> funciones = getFunctionsbyCinemaAndDate(cinema, date);
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i<funciones.size()){
            if(funciones.get(i).getMovie().getName().equals(funciones.get(i)))
                encontrado=true;
            i++;
        }
        return funciones.get(i);
    }

}
