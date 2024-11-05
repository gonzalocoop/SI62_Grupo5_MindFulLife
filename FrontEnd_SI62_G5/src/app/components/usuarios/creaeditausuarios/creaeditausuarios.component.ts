import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Usuarios } from '../../../models/Usuarios';
import { Roles } from '../../../models/Roles';
import { UsuariosService } from '../../../services/usuarios.service';
import { RolesService } from '../../../services/roles.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { map, Observable, of } from 'rxjs';

@Component({
  selector: 'app-creaeditausuarios',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './creaeditausuarios.component.html',
  styleUrl: './creaeditausuarios.component.css',
})
export class CreaeditausuariosComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  usuario: Usuarios = new Usuarios();
  //variables para trabajar el editar
  id: number = 0;
  edicion: boolean = false;
  //Para traer los elementos de dispositivos
  listaRoles: Roles[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private uS: UsuariosService,
    private rS: RolesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    //Para trabajar el editar
    this.route.params.subscribe((data: Params) => {
      //el  data['id'] es del id del parametro
      this.id = data['id'];
      this.edicion = data['id'] != null; //Si el id es diferente de null, osea que se esta ingresando con un id, entonces el edicion se vuelve true
      //data de la tabla para mostrarla
      this.init();
    });

    this.form = this.formBuilder.group({
      hcodigo: [''], //para el modificar
      husername: [
        '',
        [Validators.required, Validators.maxLength(20)],
        [this.usernameRepetido.bind(this)],
      ],
      hpassword: ['', Validators.required],
      hemail: ['', Validators.required],
      hrol: ['', [Validators.required]],
    });
    this.rS.list().subscribe((data) => {
      this.listaRoles = data;
    });
  }

  aceptar() {
    if (this.form.valid) {
      //Para el modificar
      this.usuario.id = this.form.value.hcodigo;
      this.usuario.username = this.form.value.husername;
      this.usuario.password = this.form.value.hpassword;
      this.usuario.email = this.form.value.hemail;
      this.usuario.rol.id = this.form.value.hrol;
      if (this.edicion) {
        this.uS.update(this.usuario).subscribe((data) => {
          this.uS.list().subscribe((data) => {
            this.uS.setList(data);
          });
        });
      } else {
        this.uS.insert(this.usuario).subscribe((data) => {
          this.uS.list().subscribe((data) => {
            this.uS.setList(data);
          });
        });
      }
      this.router.navigate(['usuarios']);
    } else {
      // Marca todos los campos como tocados para mostrar errores
      this.form.markAllAsTouched();
    }
  }

  init() {
    if (this.edicion) {
      this.uS.listId(this.id).subscribe((data) => {
        // Marca todos los campos como tocados para mostrar errores
        this.form.markAllAsTouched();
        this.form = new FormGroup({
          hcodigo: new FormControl(data.id, Validators.required),
          husername: new FormControl(
            data.username,
            [Validators.required, Validators.maxLength(20)],
            [this.usernameRepetido.bind(this)]
          ),
          hpassword: new FormControl(data.password, Validators.required),
          hemail: new FormControl(data.email, Validators.required),
          hrol: new FormControl(data.rol.id, [Validators.required]),
        });
        this.rS.list().subscribe((data) => {
          this.listaRoles = data;
        });
      });
    }
  }

  usernameRepetido(
    control: AbstractControl
  ): Observable<ValidationErrors | null> {
    // Si el campo está vacío, se considera válido
    if (!control.value) {
      return of(null); // Retorna válido si el campo está vacío
    }

    // Llama a la lista de cursos y verifica si hay títulos repetidos
    return this.uS.list().pipe(
      map((usuarios) => {
        // Compara títulos y excluye el curso en edición usando this.id
        const existe = usuarios.some(
          (usuario) =>
            usuario.username === control.value && usuario.id != this.id
        );
        return existe ? { usernameRepetido: true } : null;
      })
    );
  }
}
