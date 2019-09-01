
%Codigo
function varargout = Practica_3(varargin)

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Practica_3_OpeningFcn, ...
                   'gui_OutputFcn',  @Practica_3_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before Practica_3 is made visible.

function Practica_3_OpeningFcn(hObject, eventdata, handles, varargin)

axes(handles.Imagen);
image(imread('Ceti.jpg'));
set(handles.Imagen,'Xtick',[]);
set(handles.Imagen,'Ytick',[]);
set(handles.radiobutton1,'Value',1);
set(handles.radiobutton2,'Value',0);
set(handles.radiobutton3,'Value',0);
set(handles.grafica,'Visible','on');
axes(handles.grafica)
% Choose default command line output for Practica_3
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% --- Outputs from this function are returned to the command line.
function varargout = Practica_3_OutputFcn(hObject, eventdata, handles) 

varargout{1} = handles.output;

if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

% --- Executes on button press in radiobutton1.

function radiobutton1_Callback(hObject, eventdata, handles)

set(handles.radiobutton1,'Value',1);
set(handles.radiobutton2,'Value',0);
set(handles.radiobutton3,'Value',0);
fss = get(handles.txtFs,'String');
fmm = get(handles.txtFm,'String');
fs = str2num(fss);
fm = str2num(fmm);
t=[0:1/fs:1];
mensaje1 = sin(2*pi*fm*t);
handles.mensaje1 = mensaje1;
plot(mensaje1);
ylim([-1.2 1.2])
% --- Executes on button press in radiobutton2.
function radiobutton2_Callback(hObject, eventdata, handles)

set(handles.radiobutton1,'Value',0);
set(handles.radiobutton2,'Value',1);
set(handles.radiobutton3,'Value',0);
fss = get(handles.txtFs,'String');
fmm = get(handles.txtFm,'String');
fs = str2num(fss);
fm = str2num(fmm);
t=[0:1/fs:1];
mensaje2 = square(2*pi*fm*t);
handles.mensaje2 = mensaje2;
plot(mensaje2);
ylim([-1.2 1.2])
% --- Executes on button press in radiobutton3.
function radiobutton3_Callback(hObject, eventdata, handles)

set(handles.radiobutton1,'Value',0);
set(handles.radiobutton2,'Value',0);
set(handles.radiobutton3,'Value',1);
fss = get(handles.txtFs,'String');
fmm = get(handles.txtFm,'String');
fs = str2num(fss);
fm = str2num(fmm);
t=[0:1/fs:1];
mensaje3 = sawtooth(2*pi*fm*t);
handles.mensaje3 = mensaje3;
plot(mensaje3);
ylim([-1.2 1.2])

% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
close all
