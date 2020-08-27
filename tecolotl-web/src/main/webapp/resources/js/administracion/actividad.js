function seleccionActividad(evebto) {
    let seleccion = document.querySelector('input[name="actividad"]:checked');
    if (seleccion === null) {
        UIkit.modal.alert('Seleccione una actividad');
        return false;
    }
    document.getElementById('formulario-actividad:entrada-actividad').value = seleccion.id;
    return true;
}