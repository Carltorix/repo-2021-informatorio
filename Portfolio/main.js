
datosPer= document.getElementById('datos_personales');
trabajo= document.getElementById('trabajo');
estudios= document.getElementById('estudios');
botonDatosP= document.getElementById('boton_dp');
datosPer.style.display = 'block';
botonDatosP.classList.add('activo');
trabajo.style.display = 'none';
estudios.style.display = 'none';

function abrir(boton,bloque) {
    if (bloque == "datos_personales" ){
        datosPer.style.display = 'block';
        trabajo.style.display = 'none';
        estudios.style.display = 'none';
    }else if (bloque == "trabajo" ){
        datosPer.style.display = 'none';
        trabajo.style.display = 'block';
        estudios.style.display = 'none';
    }else if (bloque == "estudios" ){
        datosPer.style.display = 'none';
        trabajo.style.display = 'none';
        estudios.style.display = 'block';
    }
    grupoBoton = document.getElementById('categorias').getElementsByTagName("button");
    for(let i=0; i<grupoBoton.length;i++){
        if (grupoBoton[i].id == boton.id){
            grupoBoton[i].classList.add('activo');
        }else{
            grupoBoton[i].classList.remove('activo');
        }
    }

}

document.querySelectorAll(".item-contenido img").forEach(el=>{
    el.addEventListener("click",function(ev){
        ev.stopPropagation();
        this.parentNode.classList.add('active');
        this.parentNode.style.width = "110%";
        var grupoimage = this.parentNode.parentNode.parentNode.getElementsByTagName('img');
        for(let i=0; i<grupoimage.length;i++){
            if(grupoimage[i] != this ){
                grupoimage[i].style.display="none";
            } 
        }
        this.classList.remove('tamImage');
        this.classList.add('tamImage1');
        div= this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
        if (div.id = "estudios"){
            table = this.parentNode.parentNode.parentNode.parentNode.parentNode;
            grupoTable = div.getElementsByTagName('table');
            for(let i=0; i<grupoTable.length;i++){
                if(grupoTable[i].id != table.id ){
                    grupoTable[i].style.display="none";
                } 
            }
        }
    })
})

document.querySelectorAll(".item-contenido").forEach(el=>{
    el.addEventListener("click",function(ev){
        this.classList.remove('active');
        this.style.width = "100%";
        this.firstElementChild.classList.remove('tamImage1');
        this.firstElementChild.classList.add('tamImage');
        var grupoimage = this.parentNode.parentNode.getElementsByTagName('img');
        for(let i=0; i<grupoimage.length;i++){
            if(grupoimage[i] != this ){
                grupoimage[i].style.display="inline-block";
            } 
        }
        div= this.parentNode.parentNode.parentNode.parentNode.parentNode;
        if (div.id = "estudios"){
            table = this.parentNode.parentNode.parentNode.parentNode;
            grupoTable = div.getElementsByTagName('table');
            for(let i=0; i<grupoTable.length;i++){
                if(grupoTable[i].id != table.id ){
                    grupoTable[i].style.display="block";
                } 
            }
        }
    })
})