package ui

import actions.bean.MElement
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.CheckBox
import com.intellij.ui.components.JBOptionButton
import ui.layout.GroupLayout
import utils.LogUtil
import java.awt.*
import javax.swing.*

class AutoBindingDialog(val e: Project, val elements: MutableList<MElement>, val callback: ConfirmListener?) : DialogWrapper(true) {

    init {
        init()
    }

    override fun createNorthPanel(): JComponent? {
        return JPanel(BorderLayout(6, 0)).apply {
            add(BorderLayout.WEST, Label("View(id)").apply {
                preferredSize = Dimension(280, 38)
            })
            add(BorderLayout.CENTER, Label("findViewById"))
            add(BorderLayout.EAST, Label("Clickable"))
        }
    }

    override fun createCenterPanel(): JComponent? {
        return JPanel().apply {
            layout = BorderLayout()
            preferredSize = Dimension(600, 800)

            val jPanel = JPanel().apply {
                layout = GroupLayout(6, 5)
            }
            layout = BorderLayout()
            add(BorderLayout.CENTER, JScrollPane().apply {
                setViewportView(jPanel)
            })

            elements.forEach {
                jPanel.add(JPanel().apply {
                    layout = BorderLayout(5, 0)
                    preferredSize = Dimension(400, 42)
                    add(BorderLayout.WEST, Label("${it.name}(id=${it.id})").apply {
                        preferredSize = Dimension(280, 38)
                    })
                    add(BorderLayout.CENTER, CheckBox("", true).apply {
                        addChangeListener { checked ->
                            it.isCreateFiled = !it.isCreateFiled
                        }
                    })
                    add(BorderLayout.EAST, CheckBox("", false).apply {
                        addChangeListener { checked ->
                            it.isCreateClickMethod = !it.isCreateClickMethod
                        }
                    })
                })
            }
        }
    }

    /**
     * 底部按钮
     */
    override fun createSouthPanel(): JComponent {

        val ok = JButton("确定").apply {
            foreground = Color.ORANGE
            addActionListener {
                close(CLOSE_EXIT_CODE)
                doOKAction()
            }
        }

        val cancel = JBOptionButton(cancelAction, null)
        return JPanel(FlowLayout(FlowLayout.RIGHT)).apply {
            add(cancel)
            add(ok)
        }
    }

    override fun doOKAction() {
        LogUtil.log("doOKAction:$elements")
        callback?.onConfirm(elements)
    }

    interface ConfirmListener {
        fun onConfirm(elements: MutableList<MElement>)
    }
}