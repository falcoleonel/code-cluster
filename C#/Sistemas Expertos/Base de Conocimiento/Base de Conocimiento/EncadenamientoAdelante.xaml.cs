using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Base_de_Conocimiento
{
    /// <summary>
    /// Interaction logic for EncadenamientoAdelante.xaml
    /// </summary>
    public partial class EncadenamientoAdelante : Window
    {
        private List<Regla> reglas;
        //Bools para saber eleccion del usuario
        private char estado;
        #region eventos

        public EncadenamientoAdelante()
        {
            InitializeComponent();
            reglas = Clasificar.ListadoReglas();
            llenarReglas(reglas);
        }


        private void BtnOrganizar_Click(object sender, RoutedEventArgs e)
        {
            reglas = Clasificar.OrganizarReglas(reglas);
            llenarReglas(reglas);
            EncadenarAdelante(reglas);
            btnRevertir.IsEnabled = true;
        }


        private void BtnRevertir_Click(object sender, RoutedEventArgs e)
        {
            
            concluBox.Items.Clear();
            reglas = Clasificar.ListadoReglas();
            llenarReglas(reglas);
            btnOrganizar.IsEnabled = true;
        }

        private void BtnCancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        #endregion eventos

        #region metodos

        public List<Regla> EncadenarAdelante(List<Regla> reglas)
        {
            //variables logica de paro y movimiento del proceso
            bool proceso = true;
            List<Regla> reglasmod = reglas;
            List<Atomo> conclusiones = new List<Atomo>();
            btnOrganizar.IsEnabled = false;
            while (proceso)
            {
                
                    //pregunta por atomo en la posicion actual y se lo salta si ya existia en conclusiones
                    if (conclusiones.Any(conclusion => conclusion.Valor == reglasmod[0].Antecedentes[0].Valor && conclusion.Signo == reglasmod[0].Antecedentes[0].Signo))
                    {
                       
                        if (reglasmod[0].Antecedentes.Count < 2 )
                        {
                            conclusiones.Add(reglasmod[0].Consecuente);
                            reglasmod.Remove(reglasmod[0]);
                            break;
                        }
                        reglasmod[0].Antecedentes.Remove(reglasmod[0].Antecedentes[0]);

                    }                   
                    string Pregunta = $"¿'{reglasmod[0].Antecedentes[0].Valor}' es verdadero?";
                    MessageBoxResult result = MessageBox.Show(Pregunta,
                    "Pregunta", MessageBoxButton.YesNoCancel);
                    if (result == MessageBoxResult.Cancel)
                        break;
                    if (result == MessageBoxResult.No)
                        estado = 'f';
                    else
                        estado = 'v';

                    //logica del encadenamiento
                    Atomo aComparar = reglasmod[0].Antecedentes[0];

                    for (int reg = 0; reg < reglasmod.Count; reg++)
                    {
                        if (reglasmod[reg].Antecedentes.Any(antecedente => antecedente.Valor == aComparar.Valor))
                        {
                            if (reglasmod[reg].Antecedentes[0].Signo == estado)
                            {
                                if(!conclusiones.Contains(reglasmod[reg].Antecedentes[0]))
                                    conclusiones.Add(reglasmod[reg].Antecedentes[0]);
                                reglasmod[reg].Antecedentes.Remove(reglasmod[reg].Antecedentes[0]);
                                if (reglasmod[reg].Antecedentes.Count == 0)
                                {
                                    conclusiones.Add(reglasmod[reg].Consecuente);
                                    reglasmod.Remove(reglasmod[reg]);
                                    reg -= 1;
                                    llenarConclusiones(conclusiones);
                                    llenarReglas(reglasmod);

                                    if (reglasmod.Count == 0)
                                    {
                                        proceso = false;
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                reglasmod.Remove(reglasmod[reg]);
                                reg -= 1;
                                llenarConclusiones(conclusiones);
                                llenarReglas(reglasmod);
                            if (reglasmod.Count == 0)
                                {
                                    proceso = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(!proceso)
                    {
                        break;
                    }
                }
            
            return null;
        }

        private void llenarReglas(List<Regla> reglas)
        {
            if (reglasBox.Items.Count > 0)
                reglasBox.Items.Clear();
            foreach (Regla regla in reglas)
            {
                reglasBox.Items.Add(regla.Texto);
            }
        }
        private void llenarConclusiones(List<Atomo> conclusiones)
        {
            if (concluBox.Items.Count > 0)
                concluBox.Items.Clear();
            foreach (Atomo conclu in conclusiones)
            {
                if (conclu.Signo == 'f')
                    concluBox.Items.Add("~" + conclu.Valor);
                concluBox.Items.Add(conclu.Valor);
            }
        }

        #endregion metodos
        
    }
}