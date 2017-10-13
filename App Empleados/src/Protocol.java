public class Protocol {
    int userDNI;
    String userPassword;
    boolean validDniPasswordComination;

    public Protocol(int userDNI, String userPassword) {
        this.userDNI = userDNI;
        this.userPassword = userPassword;
    }

    public void checkValidUser(){ //tendria que retornar un boolean caso pase el checkeo (validDniPasswordCombination)
        //Envia al servidor el pedido de checkear si el dni y su password son validos

    }

}
