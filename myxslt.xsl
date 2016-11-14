<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : myxslt.xsl
    Created on : 21 августа 2016 г., 11:01
    Author     : Roma
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="*">
        <xsl:text>&#xA;</xsl:text>
        <xsl:copy>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:copy>
        <xsl:text>&#xA;</xsl:text>
    </xsl:template>
    
</xsl:stylesheet>
