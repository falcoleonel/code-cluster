using System;
using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;


namespace Base_de_Conocimiento
{
    /// <summary>
    /// Interaction logic for AgregarProposicion.xaml
    /// </summary>
    public partial class AgregarProposicion : Window
    {
        #region Constructor

        public AgregarProposicion()
        {
            InitializeComponent();
            LeerAtomos();
        }

        #endregion 

        #region Propiedades
     

        const string rutaAtomos = "..\\..\\ArchivosDeTexto\\atomos.txt";


        #endregion Propiedades

        #region Eventos

        private void btnCan_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void btnAgregar_Click(object sender, RoutedEventArgs e)
        {
            if (!(txtAtomo.Text.Equals("")))
            {
                if (!Repetido())
                {
                    if ((Regex.IsMatch(txtAtomo.Text, "^([a-z]+\\s)*[a-z]+$")))
                    {
                        using (System.IO.StreamWriter file = new System.IO.StreamWriter(rutaAtomos, true))
                        {
                            string atomo = txtAtomo.Text;
                            file.WriteLine(atomo);
                        }
                        MessageBox.Show("Atomo guardado correctamente!");
                    }
                    else
                    {
                        MessageBox.Show("Entrada no valida","Validacion");
                    }
                }

            }
            else MessageBox.Show("Ningun atomo establecido, ingrese en el textbox o salga con el boton de cancelar",
 "Validacion");
        }

        private void Window_Activated(object sender, EventArgs e)
        {
            LeerAtomos();
        }

        #endregion Eventos

        #region Metodos
        private void LeerAtomos()
        {
            if (propBox.Items.Count > 0)
                propBox.Items.Clear();
            StreamReader archivo = new StreamReader(rutaAtomos);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                propBox.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }
        private bool Repetido()
        {
            bool repetido = false;
            foreach (var item in propBox.Items)
            {
                if (item.Equals(txtAtomo.Text))
                {
                    MessageBox.Show("El átomo ya se encuentra en la lista");
                    repetido = true;
                    break;
                }
            }
            return repetido;
        }

        #endregion

        
    }
}
