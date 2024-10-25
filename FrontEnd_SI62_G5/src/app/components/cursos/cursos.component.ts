import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ListarcursosComponent } from './listarcursos/listarcursos.component';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [RouterOutlet, ListarcursosComponent],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent {
  constructor(public route:ActivatedRoute){}
}
