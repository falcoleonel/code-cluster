using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.IO;
using System.Windows.Input;

namespace AtomosYReglas
{
    /// <summary>
    /// Interaction logic for Atomos.xaml
    /// </summary>
    public partial class Atomos : Window
    {
        #region Propiedades
        string ruta = ("..\\..\\ArchivosDeTexto\\Atomos.txt");
        #endregion
        #region Constructor
        public Atomos()
        {
            InitializeComponent();
            Leer();
        }
        #endregion 
        #region Eventos
        /// <summary>
        /// Previene el ingreso de carácteres que no sean letras
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void txtAtomo_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            string atomo = txtAtomo.Text;
            {
                int ascci = Convert.ToInt32(Convert.ToChar(e.Text));

                if (ascci >= 65 && ascci <= 90 && atomo.Length == 0 || ascci >= 97 && ascci <= 122 && atomo.Length == 0 || ascci == 126 && atomo.Length == 0)
                    e.Handled = false;
                else if (ascci >= 65 && ascci <= 90 && atomo.Length == 1 && Convert.ToInt32(Convert.ToChar(txtAtomo.Text)) == 126|| ascci >= 97 && ascci <= 122 && atomo.Length == 1 && Convert.ToInt32(Convert.ToChar(txtAtomo.Text)) == 126)
                    e.Handled = false;
                else e.Handled = true;
            }
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (txtAtomo.Text.Length != 0)
                Guardar();
            else
                MessageBox.Show("No se ha ingresado átomo a guardar");

        }
        #endregion
        #region Métodos 
        /// <summary>
        /// Lee archivo para ingresar en listaAtomos, cada uno de los átomos 
        /// guardados en el archivo
        /// </summary>
        private void Leer()
        {
            if(listaAtomos.Items.Count>0)
                listaAtomos.Items.Clear();
            StreamReader archivo = new StreamReader(ruta);
            string linea = archivo.ReadLine();
            while (linea!=null)
            {
                listaAtomos.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }
        /// <summary>
        /// Guarda el átomo en el archivo
        /// </summary>
        private void Guardar()
        {
            if(!Repetido())
            {
                StreamWriter archivo = new StreamWriter(ruta, true);
                archivo.WriteLine(txtAtomo.Text);
                archivo.Close();
                MessageBox.Show("Se guardó exitosamente el átomo");
                Leer();
            }
            txtAtomo.Text = "";
            txtAtomo.Focus();
        }
        /// <summary>
        /// Valida que el átomo esté en la lista
        /// </summary>
        /// <returns></returns>
        private bool Repetido()
        {
            bool repetido=false;
            foreach(var item in listaAtomos.Items)
            {
                if (item.Equals(txtAtomo.Text))
                {
                    MessageBox.Show("El átomo ya se encuentra en la lista");
                    repetido = true;
                }
            }
            return repetido;
        }
        #endregion

    }
}
