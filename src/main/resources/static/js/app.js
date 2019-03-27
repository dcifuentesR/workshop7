/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ControllerModule = (function(){
	
	var selectedCinemaName;
	var selectedCFunctions;
    

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
    		selectedCFunctions.map(function(){
    			selectedCFunctions.seats.foreach()
    			
    		})
    		
    	};
    }
);
    
})();

