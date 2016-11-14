/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  roma
 * Created: 23.08.2016
 */

ALTER TABLE PHONES
   ADD CONSTRAINT PHONETYPES_FK Foreign Key (
      PHONETYPEID)
   REFERENCES PHONETYPES (
      ID);