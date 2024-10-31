import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
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
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Roles } from '../../../models/Roles';
import { RolesService } from '../../../services/roles.service';
import { ActivatedRoute, Params, Route, Router } from '@angular/router';
import { from, map, Observable, of } from 'rxjs';

@Component({
  selector: 'app-creaeditaroles',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatButtonModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './creaeditaroles.component.html',
  styleUrl: './creaeditaroles.component.css',
})
export class CreaeditarolesComponent {
  form: FormGroup = new FormGroup({});
  rol: Roles = new Roles();

  id: number = 0;
  edicion: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private dS: RolesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data['id'];
      this.edicion = data['id'] != null;
      this.init();
    });

    this.form = this.formBuilder.group({
      hcodigo: [''],
      hnombre: ['', [Validators.required, Validators.maxLength(20)]],
    });
    // Agrega el validador asíncrono después de la creación
    this.form.get('hnombre')?.setAsyncValidators(this.tituloRepetido.bind(this)); // Asigna el validador asíncrono al campo del título
    this.form.get('hnombre')?.updateValueAndValidity(); // Asegúrate de que el valor y la validez se actualicen
  }

  aceptar() {
    if (this.form.valid) {
      // Convertir a mayúsculas antes de guardar
      this.form.patchValue({
      hnombre: this.form.value.hnombre.toUpperCase(),
      });

      this.rol.id=this.form.value.hcodigo;
      this.rol.nombre=this.form.value.hnombre;
      if (this.edicion) {
        this.dS.update(this.rol).subscribe((data)=>{
          this.dS.list().subscribe((data)=>{
            this.dS.setList(data)
          })
        })
      }
      else{
        this.dS.insert(this.rol).subscribe((data)=>{
          this.dS.list().subscribe(data=>{
            this.dS.setList(data)
          })
        })
      }
      this.router.navigate(['/roles']);
    }
    else{
      this.form.markAllAsTouched();
    }
  }

  init(){
    if(this.edicion){
      this.dS.listId(this.id).subscribe((data)=>{
        
        this.form.markAllAsTouched();
          this.form=new FormGroup({
            hcodigo: new FormControl(data.id, Validators.required),
            hnombre: new FormControl(data.nombre, [Validators.required, Validators.maxLength(20)]),
          })
          // Agrega el validador asíncrono después de la creación
          this.form.get('hnombre')?.setAsyncValidators(this.tituloRepetido.bind(this)); // Asigna el validador asíncrono al campo del título
          this.form.get('hnombre')?.updateValueAndValidity(); // Asegúrate de que el valor y la validez se actualicen
      })
    }
  }

  tituloRepetido(control: AbstractControl): Observable<ValidationErrors | null> {
    // Verifica si el valor del control está vacío; si es así, no hay error, retorna null
    if (!control.value) {
        return of(null); // Si el campo está vacío, se considera válido
    }

    // Llama al servicio para obtener la lista de roles
    return this.dS.list().pipe(
        // Utiliza el operador map para transformar la respuesta
        map(roles => {
            // Verifica si alguno de los roles tiene el mismo nombre que el control
            // Convertimos ambos a minúsculas para una comparación insensible a mayúsculas/minúsculas
            const existe = roles.some(rol => rol.nombre.toLowerCase() === control.value.toLowerCase());

            // Si el título existe, retorna un objeto de error; de lo contrario, retorna null
            return existe ? { tituloRepetido: true } : null; // Indica que el título ya está en uso
        })
    );
  }
}
