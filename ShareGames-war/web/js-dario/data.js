//Questa funzione costruisce un oggetto di tipo Data assegnando alla var oggi e settando giorno mese ed anno
//Dentro gli if impongo che il mese il giorno e l'anno siano coerenti con l'anno corrente, il mese deve essere
//uguale a quello corrente o superiore (non posso selezionare un mese passato) e lo stesso per il girono.
//Con il comando document.write con Alessio abbiamo caricato all'interno di un documento il campo che permette
//di selezionare la data, forse conviene fare un append nell'elemento generato nel file Eventi.java dove creo dinamicamente
//il form? Alla riga: "Data: <input readonly type=text id=data name=data value="..........???


var oggi;

function crea_data(){oggi=new Date();
        var giorno=oggi.getDate();
        var mese=oggi.getMonth()+1;
        var anno=oggi.getFullYear();
        
        if(mese<10) mese="0"+mese;
        if(giorno<10)giorno="0"+mese;
        var data=giorno+"-"+mese + "-"+anno;
        
        $("#data").append("<input id=data type=date name=data min=",data," required/>");
        //document.write("<input id=data type=date name=data min=",data," required/>");
}
    
    


