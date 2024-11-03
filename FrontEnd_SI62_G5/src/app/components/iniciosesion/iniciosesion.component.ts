import { Component, OnInit } from '@angular/core';
import { Usuarios } from '../../models/Usuarios';
import { UsuariosService } from '../../services/usuarios.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import {  FormBuilder, FormGroup, ReactiveFormsModule,   } from '@angular/forms';
import { CompartiruserService } from '../../services/compartiruser.service';

@Component({
  selector: 'app-iniciosesion',
  standalone: true,
  imports: [MatFormFieldModule,MatInputModule,MatSelectModule,MatButtonModule,ReactiveFormsModule,CommonModule],
  templateUrl: './iniciosesion.component.html',
  styleUrl: './iniciosesion.component.css'
})
export class IniciosesionComponent implements OnInit{
  form:FormGroup= new FormGroup({})

  listaUsuarios: Usuarios[] = [];
  errorMessage: string | null = null; // Propiedad para el mensaje de error

  constructor(private formBuilder:FormBuilder,private uS:UsuariosService,private router:Router, private route:ActivatedRoute,private compartiruser: CompartiruserService){}

  ngOnInit(): void {
    
    this.form = this.formBuilder.group({
      husuario: [''],
    })
    this.uS.list().subscribe(data=>{
      this.listaUsuarios=data
    })
    
  
  }

  aceptar() {
    this.errorMessage = null; // Reinicia el mensaje de error al aceptar
    if (this.form.valid) {
      const selectedUser = this.listaUsuarios.find(
        user => user.id === this.form.value.husuario
      );
      if (selectedUser) {
        this.compartiruser.setSelectedUser(selectedUser); // Guarda la nueva selección
        this.router.navigate(['cursos']);
      } else {
        this.errorMessage = 'Por favor, seleccione un usuario antes de continuar.'; // Mensaje de error si no se selecciona un usuario
      }
    } else {
      this.errorMessage = 'Por favor, seleccione un usuario antes de continuar.'; // Mensaje de error si el formulario no es válido
    }
  }

  salir() {
    this.router.navigate(['/landing']); // Redirige a la ruta /landing
  }
  
}
