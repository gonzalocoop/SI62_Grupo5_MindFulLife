import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosService } from '../../../services/cursos.service';

@Component({
  selector: 'app-cursosmasymenossuscripcionesdeusuarios',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './cursosmasymenossuscripcionesdeusuarios.component.html',
  styleUrl: './cursosmasymenossuscripcionesdeusuarios.component.css'
})
export class CursosmasymenossuscripcionesdeusuariosComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  constructor(private cS: CursosService ) {}
  ngOnInit(): void {
    this.cS.CursosMasMenosSuscripcionesDeUsuarios().subscribe((data) => {
      this.barChartLabels=data.map(item=>item.nombreCurso)
      this.barChartData=
      [
        {
          data:data.map(item=>item.numUsuarios),
          label:'Cantidad de Usuarios',
          backgroundColor:['#ee3007','#f08e79','#e98215'],
          borderColor:'#e94215',
          borderWidth:1
        }
      ]
    });
  }
}
