import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { LoadEstudiantesComponent } from './load-estudiantes/load-estudiantes.component';
import { LoadPagosComponent } from './load-pagos/load-pagos.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EstudiantesComponent } from './estudiantes/estudiantes.component';
import { PagosComponent } from './pagos/pagos.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';

const routes: Routes = [
  {path: "", component: LoginComponent },
  {path:"home", component:HomeComponent},
  {path:"profile", component:ProfileComponent},
  {path:"login", component:LoginComponent},
  {path:"loadEstudiantes", component:LoadEstudiantesComponent},
  {path:"loadPagos", component:LoadPagosComponent},
  {path:"dashboard", component:DashboardComponent},
  {path:"estudiantes", component:EstudiantesComponent},
  {path:"pagos", component:PagosComponent},
  {path:"admin", component:AdminTemplateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
