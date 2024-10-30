import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
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
import { from } from 'rxjs';

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
      hnombre: ['', Validators.required],
    });
  }

  aceptar() {
    if (this.form.valid) {
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
            hnombre: new FormControl(data.nombre, Validators.required),
          })
      })
    }
  }
}
