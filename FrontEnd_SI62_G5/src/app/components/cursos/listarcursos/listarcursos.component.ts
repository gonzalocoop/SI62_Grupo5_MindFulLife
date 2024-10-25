import { Component } from '@angular/core';
import { Cursos } from '../../../models/Cursos';
import { MatTableDataSource } from '@angular/material/table';
import { CursosService } from '../../../services/cursos.service';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-listarcursos',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './listarcursos.component.html',
  styleUrl: './listarcursos.component.css'
})
export class ListarcursosComponent {
  dataSource: MatTableDataSource<Cursos> = new MatTableDataSource();
  displayedColumns:string[]=['c1','c2','c3','c4']
  constructor(private cS:CursosService){}
  ngOnInit(): void {  
    this.cS.list().subscribe(data=>{
      this.dataSource=new MatTableDataSource(data)
    })
}
}
