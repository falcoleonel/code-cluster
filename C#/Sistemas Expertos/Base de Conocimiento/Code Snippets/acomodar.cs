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
        }


        private void BtnRevertir_Click(object sender, RoutedEventArgs e)
        {

        }

        private void BtnCancel_Click(object sender, RoutedEventArgs e)
        {

        }

        #endregion eventos

        #region metodos

        public List<Regla> EncadenarAdelante(List<Regla> reglas)
        {
            //variables logica de paro y movimiento del proceso
            bool proceso = true;
            int posicion = 0;

            List<Regla> reglasmod = reglas;
            List<Atomo> conclusiones = new List<Atomo>();

            while (proceso)
            {
                //ciclo para cada antecedente de la regla actual
                for (int actual = 0; actual < reglasmod[posicion].Antecedentes.Count; actual++)
                {
                    //pregunta por atomo en la posicion actual
                    string Pregunta = $"¿'{reglasmod[posicion].Antecedentes[actual].Valor}' es verdadero?";
                    MessageBoxResult result = MessageBox.Show(Pregunta,
                    "Pregunta", MessageBoxButton.YesNoCancel);
                    if (result == MessageBoxResult.Cancel)
                        break;
                    if (result == MessageBoxResult.No)
                        estado = 'f';
                    else
                        estado = 'v';

                    //logica del encadenamiento
                    Atomo aComparar = reglasmod[posicion].Antecedentes[actual];

                    for (int reg = reglasmod.IndexOf(reglasmod[posicion]); reg < reglasmod.Count;reg++ )
                    {
                        if (reglasmod[reg].Antecedentes.Any(antecedente => antecedente.Valor == aComparar.Valor))
                        {
                            if (reglasmod[reg].Antecedentes[actual].Signo == estado)
                            {
                                conclusiones.Add(reglasmod[reg].Antecedentes[actual]);
                                reglasmod[reg].Antecedentes.Remove(reglasmod[reg].Antecedentes[actual]);
                                if (reglasmod[reg].Antecedentes.Count == 0)
                                {
                                    conclusiones.Add(reglasmod[reg].Consecuente);
                                    reglasmod.Remove(reglasmod[reg]);
                                    reg -=1;
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
                                llenarReglas(reglasmod);
                                if (reglasmod.Count == 0)
                                {
                                    proceso = false;
                                    break;
                                }

                            }
                        }
                    }
                    if (proceso)
                        actual = reglasmod[posicion].Antecedentes.Count;
                    else
                    {
                        break;
                    }
                }
            }
            btnOrganizar.IsEnabled = false;
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
                    concluBox.Items.Add("~"+conclu.Valor);
                concluBox.Items.Add(conclu.Valor);
            }
        }
        #endregion metodos


    }
}
