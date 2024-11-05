import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { UsuarioSuscripciones } from '../../../models/UsuarioSuscripciones';
import { Suscripciones } from '../../../models/Suscripciones';
import { Usuarios } from '../../../models/Usuarios';
import { UsuarioSuscripcionesService } from '../../../services/usuario-suscripciones.service';
import { SuscripcionService } from '../../../services/suscripcion.service';
import { UsuariosService } from '../../../services/usuarios.service';
import { ActivatedRoute, Params, Router } from '@angular/router';


@Component({
  selector: 'app-creaeditausuariossuscripciones',
  standalone: true,
  providers:[provideNativeDateAdapter(),{provide: MAT_DATE_LOCALE, useValue: 'es-ES' }],
  imports: [MatFormFieldModule,MatInputModule, MatSelectModule,MatDatepickerModule,MatButtonModule,ReactiveFormsModule,CommonModule],
  templateUrl: './creaeditausuariossuscripciones.component.html',
  styleUrl: './creaeditausuariossuscripciones.component.css'
})
export class CreaeditausuariossuscripcionesComponent implements OnInit{
  form:FormGroup= new FormGroup({})
  usuarioSuscripciones:UsuarioSuscripciones=new UsuarioSuscripciones()
  //variables para trabajar el editar
  id:number=0
  edicion:boolean=false
  //Para traer los elementos de sesiones
  listaSuscripciones: Suscripciones[] = [];
  listaUsuarios: Usuarios[] = [];
 
  


  constructor(private formBuilder:FormBuilder,private usS:UsuarioSuscripcionesService, private sS:SuscripcionService,private uS:UsuariosService,private router:Router, private route:ActivatedRoute){}
  ngOnInit(): void {
    //Para trabajar el editar
    this.route.params.subscribe((data:Params)=>{ //el  data['id'] es del id del parametro
      this.id=data['id'];
      this.edicion=data['id']!=null //Si el id es diferente de null, osea que se esta ingresando con un id, entonces el edicion se vuelve true
      
      //data de la tabla para mostrarla
      this.init();
    })

    this.form = this.formBuilder.group({
      hcodigo: [''], // para el modificar
      hfechaInicio: ['', Validators.required],
      hfechaFin: ['', Validators.required],
      hsuscripciones: ['', Validators.required],
      husuario: ['', Validators.required],
    })
    this.sS.list().subscribe(data=>{
      this.listaSuscripciones=data
    })
    this.uS.list().subscribe(data=>{
      this.listaUsuarios=data
    })
    
  
  }
  aceptar(){
    if(this.form.valid){
      //Para el modificar
    
      this.usuarioSuscripciones.id=this.form.value.hcodigo;

   
      this.usuarioSuscripciones.fechaInicio=this.form.value.hfechaInicio;
      this.usuarioSuscripciones.fechaFin=this.form.value.hfechaFin;
  
      this.usuarioSuscripciones.sus.id=this.form.value.hsuscripciones;
      this.usuarioSuscripciones.usu.id=this.form.value.husuario;

     
      if(this.edicion){
        this.usS.update(this.usuarioSuscripciones).subscribe((data)=>{
          this.usS.list().subscribe((data)=>{
            this.usS.setList(data)
          })
        })
      }
      else{
        this.usS.insert(this.usuarioSuscripciones).subscribe((data)=>{
          this.usS.list().subscribe(data=>{
            this.usS.setList(data)
          }) 
        }) 
      }
      this.router.navigate(['usuariossuscripciones'])
    } 
    else {
      console.log('Formulario no válido');
      // Marca todos los campos como tocados para mostrar errores
      this.form.markAllAsTouched();
    } 
    
  }

  //para el modificar
  init(){
    if(this.edicion){
      this.usS.listId(this.id).subscribe((data)=>{
        // Marca todos los campos como tocados para mostrar errores
      this.form.markAllAsTouched();
        this.form=new FormGroup({
          hcodigo:new FormControl(data.id, Validators.required),
          hfechaInicio: new FormControl(data.fechaInicio, Validators.required),
          hfechaFin: new FormControl(data.fechaFin, Validators.required),
          hsuscripciones:new FormControl(data.sus.id, Validators.required),
          husuario:new FormControl(data.usu.id, Validators.required),
        })
        this.sS.list().subscribe(data=>{
          this.listaSuscripciones=data
        })
        this.uS.list().subscribe(data=>{
          this.listaUsuarios=data
        })
        
      })
    }
  }


}
