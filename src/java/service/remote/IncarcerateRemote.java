/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.remote;

import entity.Prisoner;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author josuah
 */
@Remote
public interface IncarcerateRemote
{
    public Prisoner incarcerate(String fileNumber, String name, String surname,
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration);
}
