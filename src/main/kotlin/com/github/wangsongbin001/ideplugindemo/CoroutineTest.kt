package com.github.wangsongbin001.ideplugindemo

import com.android.tools.idea.wizard.template.impl.activities.navigationDrawerActivity.res.values.dimens
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

fun main() {
    JFrame().apply {
        size = Dimension(400, 200)
        layout = BorderLayout(10, 0)
        add(BorderLayout.NORTH, JPanel(BorderLayout(10, 0)).apply {
            preferredSize = Dimension(600, 50)
            background = Color.gray
//            border = BorderFactory.createEmptyBorder(0, 0, 0, 600)
            add(BorderLayout.WEST, JLabel("duapp_community"))

            val apply = JButton("依赖").apply {
                verticalAlignment = SwingConstants.CENTER
                preferredSize = Dimension(100, 30)
            }
            val apply1 = JLabel("本地依赖").apply {
                preferredSize = Dimension(100, 30)
            }
            var int1 = 0
//            add(BorderLayout.EAST, JPanel(BorderLayout()).apply {
//                preferredSize = Dimension(100, 30)
//                background = Color.green
//
//                add(BorderLayout.CENTER, apply1)
//                add(BorderLayout.EAST, apply)
//
//            })
            add(BorderLayout.EAST, Box.createHorizontalBox().apply {
                background = Color.green
                add(BorderLayout.CENTER, apply1)
                add(BorderLayout.EAST, apply)

            })
            val boz = Box.createHorizontalBox().apply {
                add(Box.createHorizontalGlue())
                add(JLabel("已安装"))
                add(JButton("安装").apply {
//                    preferredSize = Dimension(100, 30)
                    addActionListener(object : ActionListener {
                        override fun actionPerformed(p0: ActionEvent?) {
                            apply.isVisible = int1 % 2 == 1
                            int1++
                            apply1.isVisible = int1%2 == 1
                        }
                    })
                })
//                add(Box.createVerticalStrut(10))
//                add(JPanel(FlowLayout(FlowLayout.RIGHT)).apply {
//                    background = Color.blue
//
//                })
            }
//            add(BorderLayout.CENTER, JPanel(BorderLayout()).apply {
//                add(BorderLayout.EAST, boz)
//                add(BorderLayout.CENTER, JLabel(""))
//            })
            add(BorderLayout.CENTER, boz)
        })


        add(BorderLayout.CENTER, JPanel(BorderLayout(10, 0)).apply {
            preferredSize = Dimension(600, 50)
            background = Color.yellow
            border = BorderFactory.createEmptyBorder(0, 0, 0, 600)
            add(BorderLayout.WEST, JLabel("duapp_community"))
            add(BorderLayout.CENTER, JPanel(BorderLayout()).apply {
                add(BorderLayout.EAST, JButton("安装"))
            })
            add(BorderLayout.EAST, JPanel(FlowLayout(FlowLayout.LEFT)).apply {
                add(JLabel("本地依赖"))
                add(JButton())
            })
        })
        add(BorderLayout.SOUTH, JPanel().apply {

        })
        setBounds(300, 200, 600, 300)
    }.isVisible = true

}