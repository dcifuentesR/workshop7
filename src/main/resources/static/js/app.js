/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ControllerModule = (function(){
	
	var selectedCinemaName;
	var selectedCFunctions;
    
	var getAvailableSeatsInRow = function(row,numSeats){
		numSeats+=row.reduce(function(total,seat){
			if(seat === true)
				total+=1;
			return total;
		})
	}

    };
    
    return{
    	selectCinema:function(cinemaName){
    		apimock.getCinemaByName(cinemaName,function(error,cinema){
    	        if(error){
    	            return new Error("hubo un error")
    	        }else{
    	            selectedCinemaName= cinema.name;
    	            selectedCFunctions= cinema.functions;
    	        
    	        }
    		}
    	
    	};
    	
    	updateCinemaFunctions:function(cinemaName){
    		selectCinema(cinemaName);
    		//----------------------Map to reduced Object----------------
    		reducedFunctions = selectedCFunctions.map(function(func){
					    			var reducedFunction={};
					    			var numSeats=0;
					    			reducedFunction["name"]=func.movie.name;
					    			reducedFunction["seats"]=func.seats.foreach(getAvailableSeatsInRow(row,numSeats));
					    			reducedFunction["date"]=func.date;
					    			return reducedFunction;
					    		});
    		
    		
    	};
    }
);
    
})();

