<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <style>
                    .content {
                    float:left; clear:left;
                    font-size: 14px;
                    font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
                    }

                    .label { font-weight:bold;}
                    .row { float:left; clear:left;}

                    .green {
                    font-weight:bold;
                    color:green;
                    }
                    .red {
                    font-weight:bold;
                    color:red;
                    }
                </style>
            </head>
            <body>

                <div class="content">
                    <div class="row">
                        <span class="label">User :</span>
                        <xsl:value-of select="submission/user/firstName"/><xsl:value-of
                            select="submission/user/lastName"/>
                    </div>
                    <div class="row">
                        <span class="label">Email :</span>
                        <xsl:value-of select="submission/user/email"/>
                    </div>
                    <div class="row">
                        <span class="label">Phone :</span>
                        <xsl:value-of select="submission/user/phone"/>
                    </div>
                    <div class="row">
                        <span class="label">Final Score :</span>
                        <xsl:value-of select="submission/finalScore"/>
                    </div>

                    <xsl:for-each select="submission/session/quiz/questions/question">
                        <div class="row">
                            <p>Type :<xsl:value-of select="discriminatorValue"/>
                            </p>
                            <p>Question :<xsl:value-of select="text"/>
                            </p>
                            <table>
                                <xsl:choose>
                                    <xsl:when test="discriminatorValue='multi'">

                                        <xsl:for-each select="answers/answer">
                                            <tr>
                                                <td>
                                                    <xsl:choose>
                                                        <xsl:when
                                                                test="(checked='false' and correct='true')or(checked='true' and correct='false')">
                                                            <span class="red">X</span>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <span class="green">&#x2714;</span>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </td>
                                                <td>
                                                    <xsl:element name="input">
                                                        <xsl:attribute name="type">checkbox</xsl:attribute>
                                                        <xsl:attribute name="disabled">true</xsl:attribute>
                                                        <xsl:if test="checked='true'">
                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                        </xsl:if>
                                                    </xsl:element>
                                                </td>
                                                <td>
                                                    Answer :
                                                    <xsl:value-of select="text"/>
                                                </td>
                                            </tr>
                                        </xsl:for-each>
                                    </xsl:when>
                                    <xsl:otherwise>

                                        <xsl:for-each select="answers/answer">
                                            <tr>
                                                <td>
                                                    <xsl:choose>
                                                        <xsl:when
                                                                test="checked='true' and correct='true'">
                                                            <span class="green">&#x2714;</span>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:if test="checked='true' and correct='false'">
                                                                <span class="red">X</span>
                                                            </xsl:if>
                                                            <xsl:if test="checked='false' and correct='true'">
                                                                <span class="green">&#x2714;</span>
                                                            </xsl:if>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </td>
                                                <td>
                                                    <xsl:element name="input">
                                                        <xsl:attribute name="type">checkbox</xsl:attribute>
                                                        <xsl:attribute name="disabled">true</xsl:attribute>
                                                        <xsl:if test="checked='true'">
                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                        </xsl:if>
                                                    </xsl:element>
                                                </td>
                                                <td>
                                                    Answer :<xsl:value-of select="text"/>
                                                </td>
                                            </tr>

                                        </xsl:for-each>
                                    </xsl:otherwise>
                                </xsl:choose>

                            </table>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
