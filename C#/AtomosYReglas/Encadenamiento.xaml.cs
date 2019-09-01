using System;
using System.Collections.Generic;
using System.IO;
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

namespace AtomosYReglas
{
    /// <summary>
    /// Interaction logic for Encadenamiento.xaml
    /// </summary>
    public partial class Encadenamiento : Window
    {
        public Encadenamiento()
        {
            InitializeComponent();
        }
        #region Propiedades
        string rutaReglas = ("..\\..\\ArchivosDeTexto\\Reglas.txt");
        List<Regla> reglasOrig = new List<Regla>();
        List<Regla> reglasAuxList = new List<Regla>();
        List<Regla> reglasAuxList2 = new List<Regla>();
        List<string> conclusiones = new List<string>();
        List<string> consecuencias = new List<string>();
        bool conflicto = false;
        Regla reglaAux = new Regla();

        #endregion
        #region Métodos
        private void LeerReglas()
        {
            if (reglasOriginales.Items.Count > 0)
                reglasOriginales.Items.Clear();
            StreamReader archivo = new StreamReader(rutaReglas);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                reglasOriginales.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
            GuardarOrig();
            GuardarConsecuentes();
            while (OrdenarLista())
            {
            }
            reglasAuxList.AddRange(reglasOrig);
            reglasOriginales.Items.Clear();
            reglasFinales.Items.Clear();
            conclusiones.Clear();
            foreach (Regla regla in reglasOrig)
                reglasOriginales.Items.Add(regla.reglaCompleta);
            if (chkAdelante.IsChecked == true)
                LeerPrimerAtomo();
        }
        private void GuardarOrig()
        {
            reglasOrig.Clear();
            reglasAuxList.Clear();
            foreach (string item in reglasOriginales.Items)
            {
                reglasOrig.Add(CrearRegla(item));
            }
        }
        private void GuardarConsecuentes()
        {
            consecuencias.Clear();
            foreach (Regla regla in reglasOrig)
            {
                consecuencias.Add(regla.consecuente);
            }
        }
        private Regla CrearRegla(string item)
        {
            Regla regla = new Regla();
            regla.consecuente = item.Substring(item.IndexOf('>') + 1);
            regla.antecedentes = Antecedentes(item.Substring(0, item.IndexOf('>') - 1));
            regla.reglaCompleta = item;
            return regla;
        }
        private List<string> Antecedentes(string antecedente)
        {
            List<string> ant = new List<string>();
            for (int i = 0; i < antecedente.Length; i++)
            {
                if (antecedente[i] == '~')
                {
                    i++;
                    ant.Add("~" + antecedente[i].ToString());
                }
                else if (Regex.IsMatch(antecedente[i].ToString(), "[a-uw-z][A-UW-Z]*"))
                {
                    ant.Add(antecedente[i].ToString());
                }
            }
            return ant;
        }
        private bool OrdenarLista()
        {
            bool breakFor = false;
            for (int i = 0; i < reglasOrig.Count; i++)
            {
                if (!breakFor)
                {
                    for (int j = i; j < reglasOrig.Count; j++)
                    {
                        if (!breakFor)
                        {
                            foreach (string ant in reglasOrig[i].antecedentes)
                            {
                                if (ConsecuenteNonegado(ant) == ConsecuenteNonegado(reglasOrig[j].consecuente))
                                {
                                    reglaAux = reglasOrig[i];
                                    reglasOrig.Remove(reglaAux);
                                    reglasOrig.Add(reglaAux);
                                    breakFor = true;
                                    break;
                                }
                                else if (reglasOrig[j] == reglasOrig.Last())
                                {
                                    return false;
                                }
                            }
                        }
                        else
                            break;
                    }
                }
                else
                {
                    break;
                }
            }
            return true;
        }
        private string ConsecuenteNonegado(string consecuente)
        {
            if (consecuente[0] == '~')
                return consecuente[1].ToString();
            else
                return consecuente.ToString();
        }
        private void RecorerAtomos(string ant, string val)
        {
            if (val == "1")
            {
                if (!Conflicto(ant) && !conclusiones.Contains(ant))
                    conclusiones.Add(ant);
                else
                {
                    MessageBox.Show("Existe un conflicto con el átomo " + ant);
                    conflicto = true;
                    if(!conclusiones.Contains(ant))
                        conclusiones.Add(ant);
                    return;
                }
            }
            List<Regla> reglasAuxLocales = new List<Regla>();
            reglasAuxLocales.AddRange(reglasAuxList);
            foreach (Regla regla in reglasAuxLocales)
            {
                bool existeAtomo = false;
                Regla reglaLocal = regla;
                foreach (string antecedente in regla.antecedentes)
                {
                    if (antecedente.Contains(ant))
                        existeAtomo = true;
                }
                if (existeAtomo)
                {
                    for (int i = 0; i < reglaLocal.antecedentes.Count; i++)
                    {
                        if (reglaLocal.antecedentes[i].Contains(ant))
                        {
                            reglaLocal.antecedentes[i] = NuevoVal(reglaLocal.antecedentes[i], val);
                            ValidarRegla(reglaLocal);
                        }
                    }
                }
                if (ContieneLetras(reglaLocal.antecedentes))
                    reglasAuxList2.Add(reglaLocal);
            }
            reglasAuxList.Clear();
            reglasAuxList.AddRange(reglasAuxList2);
            reglasAuxList2.Clear();
            reglasOriginales.Items.Clear();
            reglasFinales.Items.Clear();
            foreach (Regla regla in reglasAuxList)
            {
                reglasOriginales.Items.Add(ReglaMod(regla));
            }
            foreach (string conc in conclusiones)
            {
                reglasFinales.Items.Add(conc);
            }
            LeerPrimerAtomo();

        }
        private bool Negado(string ant)
        {
            if (ant[0] == '~')
                return true;
            else
                return false;
        }
        private string NuevoVal(string ant, string val)
        {
            if (Negado(ant) && val == "1")
            {
                ant = "0";
            }
            else if (Negado(ant) && val == "0")
            {
                ant = "1";
            }
            else if (val == "1")
            {
                ant = "1";
            }
            else if (val == "0")
            {
                ant = "0";
            }
            return ant;
        }
        private string ReglaMod(Regla regla)
        {
            string reglaMod = "";
            foreach (string ant in regla.antecedentes)
            {
                if (ant != regla.antecedentes.Last())
                    reglaMod = reglaMod + ant + "^";
                else
                    reglaMod = reglaMod + ant + "->";
            }
            reglaMod = reglaMod + regla.consecuente;
            return reglaMod;
        }
        private void ValidarRegla(Regla regla)
        {
            bool valida = true;
            if (ContieneLetras(regla.antecedentes))
            {
                return;
            }
            foreach (string antecedente in regla.antecedentes)
            {
                if (antecedente == "0")
                {
                    valida = false;
                    break;
                }
            }
            if (valida)
            {
                RecorerAtomos(regla.consecuente, "1");
            }
            else
                reglasAuxList.Remove(regla);
        }
        private bool ContieneLetras(List<string> antecedentes)
        {
            foreach (string antecedente in antecedentes)
            {
                if (Regex.IsMatch(antecedente, "[a-uw-z][A-UW-Z]*") || antecedente[0] == '~')
                {
                    return true;
                }
            }
            return false;
        }
        private void LeerPrimerAtomo()
        {
            txtAtomoAdelante.Clear();
            if (reglasAuxList.Count == 0)
                return;
            foreach (Regla regla in reglasAuxList)
            {
                if (ContieneLetras(regla.antecedentes))
                    foreach (string antecedente in regla.antecedentes)
                    {
                        if (Regex.IsMatch(antecedente, "[a-uw-z][A-UW-Z]*") || antecedente[0] == '~')
                        {
                            txtAtomoAdelante.Text = antecedente[0] == '~' ? antecedente[1].ToString() : antecedente;
                            return;
                        }
                    }

            }
        }
        private bool Conflicto(string atomo)
        {
            foreach (string consecuente in conclusiones)
            {
                if (consecuente[0] == '~')
                {
                    if (atomo == consecuente[1].ToString())
                        return true;
                }
                else if (atomo[0] == '~')
                {
                    if (atomo[1].ToString() == consecuente)
                        return true;
                }
            }
            return false;
        }
        private void CompararConsec(string atomo)
        {
            List<Regla> reglasAuxLocales = new List<Regla>();
            reglasAuxLocales.AddRange(reglasAuxList);
            
            foreach(Regla regla in reglasAuxLocales)
            {
                if(regla.consecuente.Contains(atomo))
                {
                    conclusiones.Insert(0,regla.reglaCompleta);
                    reglasAuxList.Remove(regla);
                    foreach(string ant in regla.antecedentes)
                    {
                        CompararConsec(ant);
                    }
                }
            }
        }
        private void AtrasHaciaAdelante()
        {

        }
        #endregion

        #region Eventos
        //-------Encadenamiento hacia adelante-------
        #region Adelante
        private void ChkAdelante_Checked(object sender, RoutedEventArgs e)
        {
            LeerReglas();
            cmbAdelante.IsEnabled = true;
            botonAdelante.IsEnabled = true;
            ChkAtras_Unchecked(sender,e);
            chkAtras.IsChecked = false;
            conflicto = false;
        }
        private void ChkAdelante_Unchecked(object sender, RoutedEventArgs e)
        {
            cmbAdelante.IsEnabled = false;
            botonAdelante.IsEnabled = false;
        }
        private void BotonAdelante_Click(object sender, RoutedEventArgs e)
        {
            if(conflicto)
            {
                MessageBox.Show("Existe un conflicto!");
                return;
            }
            if(cmbAdelante.Text=="")
            {
                MessageBox.Show("No se ha seleccionado un valor para: " + txtAtomoAdelante.Text);
                return;
            }
            if(reglasAuxList.Count==0)
            {
                MessageBox.Show("No hay reglas para evaluar");
                return;
            }
                RecorerAtomos(txtAtomoAdelante.Text, cmbAdelante.Text);
        }
        #endregion
        //-------Encadenamiento hacia atrás-------
        #region Atrás
        private void ChkAtras_Checked(object sender, RoutedEventArgs e)
        {
            LeerReglas();
            txtAtomoAtras.IsEnabled = true;
            botonAtras.IsEnabled = true;
            ChkAdelante_Unchecked(sender,e);
            chkAdelante.IsChecked = false;
        }
        private void ChkAtras_Unchecked(object sender, RoutedEventArgs e)
        {
            txtAtomoAtras.IsEnabled = false;
            botonAtras.IsEnabled = false;
        }
        private void BotonAtras_Click(object sender, RoutedEventArgs e)
        {
            if (!consecuencias.Contains(txtAtomoAtras.Text))
            {
                MessageBox.Show("El átomo '" + txtAtomoAtras.Text + "' no es consecuente en ninguna regla");
                return;
            }
            reglasAuxList.Clear();
            reglasAuxList.AddRange(reglasOrig);
            CompararConsec(txtAtomoAtras.Text);
            reglasOriginales.Items.Clear();
            reglasFinales.Items.Clear();
            foreach (Regla regla in reglasAuxList)
                reglasOriginales.Items.Add(regla.reglaCompleta);
            foreach (string regla in conclusiones)
                reglasFinales.Items.Add(regla);
            GuardarOrig();

        }
        #endregion
        #endregion
    }

    internal class Regla
    {
        public List<string> antecedentes;
        public string consecuente;
        public string reglaCompleta;
    }
}
