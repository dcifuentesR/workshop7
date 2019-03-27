/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ControllerModule = (function(){
    var getData = apimock.getCinemaByName("Cine80",function(error,name){
        if(error){
            return new Error("hubo un error")
        }else{
            //doThings
        
        }

    },
);
    
})();

