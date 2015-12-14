var oggi;

function crea_data(){oggi=new Date();
        var giorno=oggi.getDate();
        var mese=oggi.getMonth()+1;
        var anno=oggi.getFullYear();
        
        if(mese<10) mese="0"+mese;
        if(giorno<10)giorno="0"+mese;
        var data=giorno+"-"+mese + "-"+anno;
        
        document.write("<input id=data type=date name=data min=",data," required/>");
}
    
    


